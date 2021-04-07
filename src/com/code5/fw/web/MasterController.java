package com.code5.fw.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.code5.biz.welcome.Welcome;
import com.code5.fw.data.Box;
import com.code5.fw.data.BoxHttp;

public class MasterController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 *	@Param HttpServletRequest request : was에서 넘겨준 객체
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		
		Box box = new BoxHttp(request);
		BoxContext.setThread(box);
		
		try {
			Welcome welcome = new Welcome();
			String jsp = welcome.execute();
			
			// was에서 재공한 request, response를 모델에 제공해주는 역할
			RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			BoxContext.removeThread();
		}
	}
}
