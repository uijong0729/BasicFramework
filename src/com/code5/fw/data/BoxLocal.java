package com.code5.fw.data;

import java.util.HashMap;

/**
 * @author kroch
 *
 */
/**
 * @author kroch
 *	WAS없이 로직 수행가능한 객체 (테스트용)
 */
public class BoxLocal extends Box {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private HashMap<String, Object> hm = new HashMap<String, Object>();

	/**
	 * 
	 */
	public BoxLocal() {

	}

	/**
	 * 
	 */
	public Object get(String key) {
		return hm.get(key);
	}

	/**
	 * 
	 */
	public void put(String key, Object obj) {
		hm.put(key, obj);
	}

	/**
	 *
	 */
	public void setSessionB(SessionB sessionB) {
		hm.put(KEY_SESSIONB, sessionB);
	}

}
