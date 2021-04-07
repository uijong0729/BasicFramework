package test.welcome;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import com.code5.biz.welcome.Welcome;

import junit.framework.TestCase;

public class welcome_test extends TestCase {
	
	@Test
	public void test01() throws Exception{
		String name = "abcd";
		
		// 프레임워크에서 테스트를 제공하기 위해 이 객체를 생성할 방법?? -> Put
		HttpServletRequest request = null;
		// 사용자가 전달하는 입력값을 받을 방법?? 
		//request.setParameter("name", name); -> Put
		
		Welcome welcome = new Welcome();
		welcome.execute(request);
		
		String msg = (String) request.getAttribute("msg"); // Get
		
		assertEquals("welcome = " + name, msg);
	}
}
