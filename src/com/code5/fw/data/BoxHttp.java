package com.code5.fw.data;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kroch
 *
 */
public class BoxHttp extends Box {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private HttpServletRequest request = null;

	/**
	 * @param request
	 */
	public BoxHttp(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 
	 */
	public Object get(String key) {

		Object obj = request.getAttribute(key);
		if (obj != null) {
			return obj;
		}

		return request.getParameter(key);
	}

	/**
	 *	public static String KEY_SESSIONB 
	 *		= "com.code5.fw.web.KEY_SESSIONB";
	 *
	 *	멤버변수 request에서 얻을 수 있는 세션 정보는 보존해두어 매번 세션에서 Attribute를 취득하는 비용을 줄인다
	 */
	public void setSessionB(SessionB sessionB) {
		request.setAttribute(KEY_SESSIONB, sessionB);
		request.getSession().setAttribute(KEY_SESSIONB, sessionB);
	}

	@Override
	public void put(String key, Object obj) {
		request.setAttribute(key, obj);
	}
	
}
