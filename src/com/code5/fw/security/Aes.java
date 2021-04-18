package com.code5.fw.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.code5.fw.data.Hex;

/**
 * @author kroch
 *
 */
/**
 * @author kroch
 *	128비트 암호화 알고리즘 (하나의 블록크기 = 128비트)
 *	- 커다란 평문이 한꺼번에 암호화 되는 것이 아니고 블록 단위로 분할하여 각 블록을 암호화
 *	- 각 블록 끼리는 연관성을 부여
 */
public class Aes {

	private byte[] key = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	
	/**
	 * 	이니셜 벡터
	 */
	private byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
	private IvParameterSpec ivSpec = new IvParameterSpec(iv);

	/**
	 * @param plan
	 * @return
	 * @throws Exception
	 * 
	 * 	암호화
	 */
	public String encrypt(String s) throws Exception {

		byte[] plan = s.getBytes();
		// 3가지 규칙 지정 : AES, CBC, PKCS5Padding
		Cipher encrypter = Cipher.getInstance("AES/CBC/PKCS5Padding");
		encrypter.init(1, keySpec, ivSpec);
		byte[] enc = encrypter.doFinal(plan);

		// 헥사 또는 Base64를 사용하는데 여기선 헥사를 사용 
		return Hex.byteToHex(enc);

	}

	/**
	 * @param s
	 * @return
	 * @throws Exception
	 * 
	 * 	복호화
	 */
	public String decrypt(String s) throws Exception {

		byte[] enc = Hex.hexToByte(s);

		Cipher decrypter = Cipher.getInstance("AES/CBC/PKCS5Padding");
		decrypter.init(2, keySpec, ivSpec);
		byte[] plan = decrypter.doFinal(enc);

		String plans = new String(plan);

		return plans.trim();

	}

}
