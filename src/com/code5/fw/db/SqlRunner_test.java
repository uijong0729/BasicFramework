package com.code5.fw.db;

import com.code5.fw.data.Box;
import com.code5.fw.data.Table;
import com.code5.fw.web.BoxContext;
import com.code5.fw.web.TransactionContext;

import junit.framework.TestCase;

public class SqlRunner_test extends TestCase {
	
	// SQL ���������� �ذ��ϱ� ���� ���

	// 1. SQL �� JAVA �ڵ� �и� : EMP001D_01, EMP001D_02, FW_SQL
	// 2. ���� SQL, ���� SQL ������ ����� ���� ��� : sql.getTable, sql.execute
	// 3. �÷����� ������ �ذ� : Table

	// TDD �׽�Ʈ�ڵ�� �䱸������ ���� �����ϰ� �� Ʋ�� �´� �ڵ带 ���� ����� ���

	/**
	 * @throws Exception
	 */
	public void test_01() throws Exception {

		Box box = BoxContext.getThread();
		box.put("EMP_NM", "ABC");

		SqlRunner sql = SqlRunner.getSqlRunner();

		Table table = sql.getTable("EMP001D_01");

		assertEquals(2, table.size());

		String[] cols = table.getCols();

		assertEquals("EMP_N", cols[0]);
		assertEquals("EMP_NM", cols[1]);
		assertEquals("HP_N", cols[2]);

		assertEquals("N01", table.s("EMP_N", 0));
		assertEquals("N03", table.s("EMP_N", 1));

	}

	/**
	 * @throws Exception
	 */
	public void test_02() throws Exception {

		Box box = BoxContext.getThread();
		box.put("EMP_N", "N01");
		box.put("HP_N", "010-2222-3333");

		SqlRunner sql = SqlRunner.getSqlRunner();

		int i = sql.executeSql("EMP001D_02");

		assertEquals(1, i);

		TransactionContext.commit();

	}
}
