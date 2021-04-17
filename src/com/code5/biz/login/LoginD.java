package com.code5.biz.login;

import java.sql.SQLException;

import com.code5.fw.data.Table;
import com.code5.fw.db.SqlRunner;

/**
 * @author kroch
 *
 */
public class LoginD {

	/**
	 * 
	 */
	private SqlRunner sql = SqlRunner.getSqlRunner();

	/**
	 * 
	 */
	public static LoginD getLoginD() {
		return new LoginD();
	}

	private static String FORM_NO_01 = "LOGIND_01";
	private static String FORM_NO_02 = "LOGIND_02";
	private static String FORM_NO_03 = "LOGIND_03";

	/**
	 * 
	 * ID �� �������� BZ_ID ��ȸ
	 * 
	 * @return
	 * @throws SQLException
	 */
	Table login_01() throws SQLException {
		return sql.getTable(FORM_NO_01);
	}

	/**
	 * 
	 * ����ȸ�� ����
	 * 
	 * @return
	 * @throws SQLException
	 */
	int login_02() throws SQLException {
		return sql.executeSql(FORM_NO_02);
	}

	/**
	 * 
	 * �α��� ���� ó��
	 * 
	 * @return
	 * @throws SQLException
	 */
	int login_03() throws SQLException {
		return sql.executeSql(FORM_NO_03);
	}
}
