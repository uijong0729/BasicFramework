package com.code5.fw.web;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.code5.fw.data.Box;
import com.code5.fw.data.BoxHttp;
import com.code5.fw.db.Transaction;
import com.code5.fw.db.TransactionSQLServerPool;

/**
 * @author zero
 *
 */
public class MasterController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Box box = new BoxHttp(request);
		BoxContext.setThread(box);

		Transaction transaction = new TransactionSQLServerPool();
		TransactionContext.setThread(transaction);

		try {

			String KEY = request.getPathInfo().substring(1);

			String JSP_KEY = execute(KEY);

			MasterControllerD dao = new MasterControllerD();
			Box view = dao.getView(JSP_KEY);
			String JSP = view.s("JSP");

			TransactionContext.commit();

			RequestDispatcher dispatcher = request.getRequestDispatcher(JSP);
			dispatcher.forward(request, response);

		} catch (Exception ex) {

			ex.printStackTrace();
			try {
				TransactionContext.rollback();
			} catch (SQLException exx) {
				exx.printStackTrace();
				throw new ServletException(exx);
			}

		} finally {

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

		// TODO [4]
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
