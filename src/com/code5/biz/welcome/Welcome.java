package com.code5.biz.welcome;

import javax.servlet.http.*;

import com.code5.fw.data.Box;
import com.code5.fw.web.BoxContext;

public class Welcome {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public String execute() {
		Box box = BoxContext.getThread();
		
		//String name = request.getParameter("name");
		String msg = "welcome = " + box.getString("name");
		
		//request.setAttribute("msg", msg);
		box.put("msg", msg);

		// was기준의 경로 
		return "/WEB-INF/classes/com/code5/biz/welcome/welcome.jsp";
	}
}