package com.code5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.code5.biz.welcome.Welcome;

public class MasterController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		
		Welcome welcome = new Welcome();
		String jspUrl = welcome.execute(request);
		
		// was���� ����� request, response�� �𵨿� �������ִ� ����
		RequestDispatcher dispatcher = request.getRequestDispatcher(jspUrl);
		dispatcher.forward(request, response);
	}
	
}
