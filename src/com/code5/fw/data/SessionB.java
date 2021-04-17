package com.code5.fw.data;

import java.io.Serializable;

/**
 * @author zero
 *
 */
/**
 * @author kroch
 * 	- Serializable : �ƹ��͵� ���� ��ũ�� �������̽� 
 *	- 1. ��Ű(������) JSESSIONID 
 *  - 2. SessionB 
 *  	: ���⼭�� SessionB�� �ּ�ȭ�� ��ɸ� ���� ���� ��ü�� �� (�� ������ Serializable �ʼ�)
 *	- 3. ������(�ֱ����ġ : WAS�� �޸�) 
 *		: WAS�� ���� �����Ͽ� ���������� �и��Ǿ� ���� �� ����
 *		: ���������� �и��� ��� ��� WAS�� ���ϴ��Ŀ� ���� ���ǿ� ������ �ְų� ���� �� ����
 *	- 4. Ŭ������ ������ ���丮��(���������ġ) 
 *		: Ŭ������ ������ ����� ������ �����ϴ� ������ �� WAS�� ������ �������� ����
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
	 * �ѹ� �����Ǹ� ������ ���ϵ��� get�޼ҵ常 ����
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
