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
 *	128��Ʈ ��ȣȭ �˰��� (�ϳ��� ���ũ�� = 128��Ʈ)
 *	- Ŀ�ٶ� ���� �Ѳ����� ��ȣȭ �Ǵ� ���� �ƴϰ� ��� ������ �����Ͽ� �� ����� ��ȣȭ
 *	- �� ��� ������ �������� �ο�
 */
public class Aes {

	private byte[] key = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	
	/**
	 * 	�̴ϼ� ����
	 */
	private byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	private SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
	private IvParameterSpec ivSpec = new IvParameterSpec(iv);

	/**
	 * @param plan
	 * @return
	 * @throws Exception
	 * 
	 * 	��ȣȭ
	 */
	public String encrypt(String s) throws Exception {

		byte[] plan = s.getBytes();
		// 3���� ��Ģ ���� : AES, CBC, PKCS5Padding
		Cipher encrypter = Cipher.getInstance("AES/CBC/PKCS5Padding");
		encrypter.init(1, keySpec, ivSpec);
		byte[] enc = encrypter.doFinal(plan);

		// ��� �Ǵ� Base64�� ����ϴµ� ���⼱ ��縦 ��� 
		return Hex.byteToHex(enc);

	}

	/**
	 * @param s
	 * @return
	 * @throws Exception
	 * 
	 * 	��ȣȭ
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
