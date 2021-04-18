package com.code5.biz.emp;


import com.code5.fw.data.Box;
import com.code5.fw.data.Table;
import com.code5.fw.web.BizController;
import com.code5.fw.web.BoxContext;

/**
 * @author kroch
 *
 */
public class Emp001 implements BizController {

	/**
	 * @return
	 * @throws Exception
	 * 
	 *                   ������� ��ȸ
	 */
	public String emp00110() throws Exception {

		Box box = BoxContext.getThread();

		Emp001D dao = new Emp001D();
		Table table = dao.emp00110();

		box.put("table", table);

		return "emp00110";
	}

	/**
	 * @return
	 * @throws Exception
	 * 
	 *                   ������� �� �޴��� ��ȣ ����
	 */
	public String emp00120() throws Exception {

		Box box = BoxContext.getThread();

		Emp001D dao = new Emp001D();
		Table table = dao.emp00110();
		for (int i = 0; i < table.size(); i++) {
			String THIS_EMP_N = table.s("EMP_N", i);
			box.put("EMP_N", THIS_EMP_N);
			if (dao.emp00120() != 1) {
				throw new Exception();
			}
		}

		return emp00110();
	}
}
