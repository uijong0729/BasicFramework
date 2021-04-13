package com.code5.fw.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.code5.biz.welcome.Welcome;
import com.code5.fw.data.Box;
import com.code5.fw.data.BoxHttp;
import com.code5.fw.db.Transaction;
import com.code5.fw.db.TransactionSQLServerPool;

public class MasterController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * TransactionContext를 사용 Transaction 객체를 비즈니스 로직에 전달
	 * commit과 roll-back기준 정의
	 * 
	 * conn 객체 생명주기 확인
	 * new conn()
	 * conn.commit()
	 * conn.rollback()
	 * conn.close()
	 * 
	 *	@Param HttpServletRequest request : was에서 넘겨준 객체
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		
		Box box = new BoxHttp(request);
		BoxContext.setThread(box);
		
		Transaction transaction = new TransactionSQLServerPool();
		TransactionContext.setThread(transaction);
		
		try {
			// "/login?isLogin=true" 에서 첫번째 문자 "/"를 버림
			String key = request.getPathInfo().substring(1);
			String jspKey = execute(key);
			
			MasterControllerD dao = new MasterControllerD();
			Box view = dao.getView(jspKey);
			String jsp = view.s("JSP");
			
			// execute 성공시 커밋
			TransactionContext.commit();
			
			// was에서 재공한 request, response를 모델에 제공해주는 역할
			RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			try {
				// execute 실패시 롤백
				TransactionContext.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new ServletException(e1);
			}
		}finally {
			TransactionContext.removeThread();
			BoxContext.removeThread();
		}
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 * 
	 * 
	 */
	public static String execute(String KEY) throws Exception {

		MasterControllerD dao = new MasterControllerD();

		Box controller = dao.getController(KEY);
		String CLASS_NAME = controller.s("CLASS_NAME");
		String METHOD_NAME = controller.s("METHOD_NAME");

		// TODO [4] 동적 바인딩 -> 리플렉션 코드로 구현 
		@SuppressWarnings("rawtypes")
		Class newClass = Class.forName(CLASS_NAME);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		Constructor constructor = newClass.getConstructor();

		Object instance = constructor.newInstance();

		if (!(instance instanceof BizController)) {
			throw new Exception();
		}

		Method method = instance.getClass().getDeclaredMethod(METHOD_NAME);

		// TODO [5]
		String JSP_KEY = (String) method.invoke(instance);
		return JSP_KEY;

	}

}
