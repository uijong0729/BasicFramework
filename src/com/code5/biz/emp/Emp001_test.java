package com.code5.biz.emp;

import com.code5.fw.data.Box;
import com.code5.fw.data.SessionB;
import com.code5.fw.web.BoxContext;
import com.code5.fw.web.MasterController;
import com.code5.fw.web.TransactionContext;

import junit.framework.TestCase;

/**
 * @author zero
 *
 */
/**
 * @author kroch
 *	
 *	IoC관점 : setUp() -> [테스트 동작수행] -> tearDown()  
 */
public class Emp001_test extends TestCase {

	/**
	 *	매 테스트 케이스마다 전처리를 수행 
	 */
	@Override
	protected void setUp() throws Exception {
		SessionB user = new SessionB("id_U0", "U0", "10.1.1.1");
		Box box = BoxContext.getThread();
		box.setSessionB(user);
	}

	/**
	 *	매 테스트 케이스마다 후처리를 수행 
	 */
	@Override
	protected void tearDown() throws Exception {
		TransactionContext.commit();
	}

	/**
	 * @throws Exception
	 */
	public void test_emp00110() throws Exception {

		MasterController.execute("emp00110");

	}

	/**
	 * @throws Exception
	 * 
	 * 이 로직이 실행될 경우 권한 에러가 발생함
	 */
	public void test_emp00120() throws Exception {

		MasterController.execute("emp00120");
	}

}
