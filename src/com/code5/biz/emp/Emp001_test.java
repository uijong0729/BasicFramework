package com.code5.biz.emp;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;

import com.code5.fw.data.Box;
import com.code5.fw.web.BoxContext;

import junit.framework.TestCase;

public class Emp001_test extends TestCase {
	
	// TDD : 테스트 주도 개발
	// 확인을 위한 반복적인 행위들은 자동화
	// 테스트 케이스 실행으로 디버그 포인트를 지정해서 확인하기에도 용이
	public void test00110() throws Exception {
		Box box = BoxContext.getThread();
		box.put("EMP_NM", "ABC");

		Emp001 emp001 = new Emp001();
		emp001.emp00110();
	}

	public void test00120() throws Exception {
		Emp001 emp001 = new Emp001();
		emp001.emp00120();
	}
}
