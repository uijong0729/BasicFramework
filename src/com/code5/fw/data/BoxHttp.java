package com.code5.fw.data;

import javax.servlet.http.HttpServletRequest;

// HttpServletRequest용 
public class BoxHttp extends Box{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request = null;
	
	public BoxHttp(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
	}
	
	@Override
	public Object get(String key) {
		// getAttribute:비즈니스 로직 데이터 (우선순위 1)
		Object obj = request.getAttribute(key);
		if (obj != null) {
			return obj;
		}
		
		// getParameter:사용자 입력 데이터 (우선순위 2)
		return request.getParameter(key);
	}
	
	@Override
	public void put(String key, Object obj) {
		request.setAttribute(key, obj);		
	}

	@Override
	public void setSessionB(SessionB sessionB) {
		// TODO Auto-generated method stub
		
	}
}
