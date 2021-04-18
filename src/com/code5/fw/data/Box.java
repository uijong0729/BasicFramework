package com.code5.fw.data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author kroch
 *
 */
public abstract class Box implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param key
	 * @param object
	 * 
	 *
	 */
	public abstract void put(String key, Object object);

	/**
	 * @param key
	 * @return
	 * 
	 * 
	 */
	public abstract Object get(String key);

	/**
	 * @param key
	 * @return
	 * 
	 * 
	 */
	public String getString(String key) {

		Object s = get(key);

		if (s == null) {
			return "";
		}

		if (!(s instanceof String)) {
			return "";

		}
		return (String) s;
	}

	/**
	 * @param key
	 * @param defaultx
	 * @return
	 * 
	 * 
	 */
	public String getString(String key, String defaultx) {

		String s = getString(key);

		if ("".equals(s)) {
			return defaultx;
		}

		return s;

	}

	/**
	 * @param key
	 * @return
	 * 
	 * 
	 */
	public String s(String key) {
		return getString(key);
	}

	/**
	 * @param key
	 * @param defualtx
	 * @return
	 * 
	 * 
	 */
	public String s(String key, String defualtx) {
		return getString(key, defualtx);
	}

	/**
	 * @param key
	 * @return
	 * 
	 * 
	 */
	public int getInt(String key) {

		String s = s(key);
		if ("".equals(s)) {
			return 0;
		}

		try {

			return Integer.parseInt(s);

		} catch (Exception ex) {

			return 0;
		}

	}

	/**
	 * @param key
	 * @return
	 * 
	 * 
	 */
	public BigDecimal getBigDecimal(String key) {

		String s = s(key);
		if ("".equals(s)) {
			return BigDecimal.ZERO;
		}

		try {

			return new BigDecimal(s);

		} catch (Exception ex) {

			return BigDecimal.ZERO;
		}

	}

	/**
	 * @param key
	 * @return
	 */
	public Table getTable(String key) {

		Object obj = get(key);
		if (obj == null) {
			return new Table();
		}

		if (obj instanceof Table) {
			return (Table) obj;

		}

		return new Table();

	}

	/**
	 * @param key
	 * @return
	 */
	public boolean getBoolean(String key) {

		String s = s(key);
		if ("true".equals(s)) {
			return true;
		}

		return false;

	}

	/**
	 * @param key
	 * @param b
	 * @return
	 */
	public void put(String key, boolean b) {
		put(key, "" + b);
	}

	//----------------------------------------------------------------
	// 입력출력을 공통화한 추상화 객체 Box에 SessionB를 저장하는 이유
	//  - 세션에서 가져오는 것이 아닌, 내부 자료구조 HashMap에서 데이터를 취득하기 위함
	//----------------------------------------------------------------
	
	/**
	 * 
	 */
	public static String KEY_SESSIONB = "com.code5.fw.web.KEY_SESSIONB";

	/**
	 * 
	 */
	public static String KEY_REMOTE_ADDR = "com.code5.fw.web.KEY_REMOTE_ADDR";

	/**
	 * 
	 */
	public static String KEY_SERVICE_KEY = "com.code5.fw.web.KEY_SERVICE_KEY";

	/**
	 * @param sessionB
	 */
	public abstract void setSessionB(SessionB sessionB);

	/**
	 * @return
	 */
	public SessionB getSessionB() {
		return (SessionB) get(KEY_SESSIONB);
	}
}
