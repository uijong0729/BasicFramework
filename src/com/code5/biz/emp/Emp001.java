package com.code5.biz.emp;

import org.junit.jupiter.api.AfterAll;

import com.code5.fw.data.Box;
import com.code5.fw.data.Table;
import com.code5.fw.web.BoxContext;
import com.code5.fw.web.TransactionContext;

/**
 * @author zero
 *
 */
public class Emp001 {

	// SQL ���������� �ذ��ϱ� ���� ���
	// 1. SQL �� JAVA �ڵ� �и� -> KEY
	// 2. ���� SQL, ���� SQL ������ ����� ���� ��� -> getTable, executeSql
	// 3. �÷����� ������ �ذ� -> Table
	
	/**
	 * @return
	 * @throws Exception
	 * 
	 * ������� ��ȸ
	 */
	public String emp00110() throws Exception{
		Box box = BoxContext.getThread();
		Emp001D dao = new Emp001D();
		Table table = dao.emp00101();
		box.put("table", table);
		
		return "emp00110";
	}
	
	/**
	 * @return
	 * @throws Exception
	 * 
	 * ������� �� ��ȣ ����
	 */
	public String emp00120() throws Exception{
		return emp00110();
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public String emp00101() throws Exception {

		Box box = BoxContext.getThread();

		Emp001D dao = new Emp001D();
		Table table = dao.emp00101();
		box.put("table", table);

		return "/WEB-INF/classes/com/code5/biz/emp/emp00101.jsp";

	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String emp00102() throws Exception {

		emp00101();

		Box box = BoxContext.getThread();
		Table table = box.getTable("table");

		Emp001D dao = new Emp001D();

		for (int i = 0; i < table.size(); i++) {

			box.put("EMP_N", table.getData("EMP_N", i));

			int updateCnt = dao.emp00102();
			if (updateCnt != 1) {
				throw new Exception();
			}
		}

		TransactionContext.commit();

		return emp00101();
	}
}
