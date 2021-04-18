package com.code5.fw.security;

import java.security.MessageDigest;

import com.code5.fw.data.Hex;

/**
 * @author kroch
 *
 */
/**
 * @author kroch
 *	��й�ȣ ��ȣȭ 
 */
public class CryptPin {

	/**
	 * @param data
	 * @param salt : �ұ� (salt�����Ϳ� ���� : ���� ��ȣ�� ª�� ��ȣ������ ��� ����)
	 * @return
	 * 
	 * 	- �ؽ� �Լ��� ��ȣȭ�� ���κ��� ���̺� ���ݿ� ���
	 *  - ��ȣȭ�� ��й�ȣ�� �ִٰ� ���� �� ���κ��� ���̺��� ���� ������ ��й�ȣ�� ������ �� �ְԵȴ�.
	 *  - salt�����͸� ���������ν� ���� ��й�ȣ�� �Ȱ��� ���δٸ� ������ ��й�ȣ�� ��쿡�� �ٸ� �����͸� �����Ѵ�. 
	 */
	public static String cryptPin(String pin, String salt) {

		try {

			pin = pin + "-" + salt;

			byte[] b = pin.getBytes();

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(b);
			byte[] enc = md.digest();
			
			// ��罺Ʈ�� ���·� ����
			return Hex.byteToHex(enc);

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

}
