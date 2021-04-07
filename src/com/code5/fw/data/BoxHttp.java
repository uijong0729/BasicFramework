package com.code5.fw.data;

import javax.servlet.http.HttpServletRequest;

public class BoxHttp extends Box{
	
	private HttpServletRequest request = null;
	
	public BoxHttp(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
	}
	
	@Override
	public Object get(String key) {
		// getAttribute:����Ͻ� ���� ������ (�켱���� 1)
		Object obj = request.getAttribute(key);
		if (obj != null) {
			return obj;
		}
		
		// getParameter:����� �Է� ������ (�켱���� 2)
		return request.getParameter(key);
	}
	
	@Override
	public void put(String key, Object obj) {
		request.setAttribute(key, obj);		
	}
}
