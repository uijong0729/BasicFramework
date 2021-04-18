package com.code5.fw.security;

import java.security.MessageDigest;

import com.code5.fw.data.Hex;

/**
 * @author kroch
 *
 */
/**
 * @author kroch
 *	비밀번호 암호화 
 */
public class CryptPin {

	/**
	 * @param data
	 * @param salt : 소금 (salt데이터와 결합 : 기존 암호가 짧은 암호일지라도 길게 늘임)
	 * @return
	 * 
	 * 	- 해쉬 함수의 암호화는 레인보우 테이블 공격에 취약
	 *  - 암호화된 비밀번호가 있다고 했을 때 레인보우 테이블을 통해 원래의 비밀번호를 유추할 수 있게된다.
	 *  - salt데이터를 가미함으로써 원본 비밀번호가 똑같은 서로다른 유저의 비밀번호인 경우에도 다른 데이터를 리턴한다. 
	 */
	public static String cryptPin(String pin, String salt) {

		try {

			pin = pin + "-" + salt;

			byte[] b = pin.getBytes();

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(b);
			byte[] enc = md.digest();
			
			// 헥사스트링 형태로 리턴
			return Hex.byteToHex(enc);

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

}
