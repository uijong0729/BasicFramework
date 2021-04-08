package com.code5.fw.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.io.PrintWriter;
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
			Welcome welcome = new Welcome();
			
			// execute 내 처리가 하나의 트랜잭션 
			String jsp = welcome.execute();
			
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
}
