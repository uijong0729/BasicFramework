package com.code5.biz.emp;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;

import com.code5.fw.data.Box;
import com.code5.fw.web.BoxContext;

import junit.framework.TestCase;

public class Emp001_test extends TestCase {
	
	// TDD : �׽�Ʈ �ֵ� ����
	// Ȯ���� ���� �ݺ����� �������� �ڵ�ȭ
	// �׽�Ʈ ���̽� �������� ����� ����Ʈ�� �����ؼ� Ȯ���ϱ⿡�� ����
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
