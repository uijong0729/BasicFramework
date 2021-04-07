package test.welcome;

import com.code5.biz.welcome.Welcome;
import com.code5.fw.data.Box;
import com.code5.fw.web.BoxContext;

import junit.framework.TestCase;

/**
 * @author kroch
 *	JUnit : �׽�Ʈ �޼ҵ带 ã�Ƽ� ����
 *
 *	WAS���� �׽�Ʈ�� �����ϵ��� Box�� �߻�ȭ 
 */
public class welcome_test extends TestCase {

	public void test01() throws Exception{
		String name = "abcd";
		
		// �����ӿ�ũ���� �׽�Ʈ�� �����ϱ� ���� �� ��ü�� ������ ���?? -> Put
		//HttpServletRequest request = null;
		Box box = BoxContext.getThread();
		
		// ����ڰ� �����ϴ� �Է°��� ���� ���?? 
		//request.setParameter("name", name); -> Put
		box.put("name", name);
		
		Welcome welcome = new Welcome();
		welcome.execute();
		
		//String msg = (String) request.getAttribute("msg"); // Get
		String msg = box.getString("msg"); // Get
		
		assertEquals("welcome = " + name, msg);
	}
}
