package com.code5.biz.welcome;

import javax.servlet.http.*;

public class Welcome {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String execute(HttpServletRequest request) {
		String name = request.getParameter("name");
		String msg = "welcome = " + name;
		
		request.setAttribute("msg", msg);

		// was기준의 경로 
		return "/WEB-INF/classes/com/code5/biz/welcome/welcome.jsp";
	}
}