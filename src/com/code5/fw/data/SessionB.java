package com.code5.fw.data;

import java.io.Serializable;

/**
 * @author zero
 *
 */
/**
 * @author kroch
 * 	- Serializable : 아무것도 없는 마크업 인터페이스 
 *	- 1. 쿠키(브라우저) JSESSIONID 
 *  - 2. SessionB 
 *  	: 여기서는 SessionB는 최소화된 기능만 담은 세션 객체로 씀 (이 때문에 Serializable 필수)
 *	- 3. 페이지(주기억장치 : WAS의 메모리) 
 *		: WAS는 복수 존재하여 물리적으로 분리되어 있을 수 있음
 *		: 물리적으로 분리된 경우 어느 WAS를 통하느냐에 따라 세션에 정보가 있거나 없을 수 있음
 *	- 4. 클러스터 서버의 스토리지(보조기억장치) 
 *		: 클러스터 서버에 저장된 세션을 참조하는 것으로 각 WAS의 세션을 공통으로 관리
 *	
 */
public class SessionB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String id = null;

	/**
	 * 
	 */
	private String auth = null;

	/**
	 * 
	 */
	private String ip = null;

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getAuth() {
		return auth;
	}

	/**
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 한번 설정되면 변경을 못하도록 get메소드만 제공
	 * 
	 * @param id
	 * @param auth
	 * @param ip
	 */
	public SessionB(String id, String auth, String ip) {

		this.id = id;
		this.auth = auth;
		this.ip = ip;
	}

}
