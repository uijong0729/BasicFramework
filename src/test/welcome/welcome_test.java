package test.welcome;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import com.code5.biz.welcome.Welcome;

import junit.framework.TestCase;

public class welcome_test extends TestCase {
	
	@Test
	public void test01() throws Exception{
		String name = "abcd";
		
		// �����ӿ�ũ���� �׽�Ʈ�� �����ϱ� ���� �� ��ü�� ������ ���?? -> Put
		HttpServletRequest request = null;
		// ����ڰ� �����ϴ� �Է°��� ���� ���?? 
		//request.setParameter("name", name); -> Put
		
		Welcome welcome = new Welcome();
		welcome.execute(request);
		
		String msg = (String) request.getAttribute("msg"); // Get
		
		assertEquals("welcome = " + name, msg);
	}
}
