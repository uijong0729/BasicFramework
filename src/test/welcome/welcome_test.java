package test.welcome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.code5.biz.welcome.Welcome;
import com.code5.fw.data.Box;
import com.code5.fw.web.BoxContext;

import junit.framework.TestCase;

/**
 * @author kroch JUnit : �׽�Ʈ �޼ҵ带 ã�Ƽ� ����
 *
 *         WAS���� �׽�Ʈ�� �����ϵ��� Box�� �߻�ȭ
 */
public class welcome_test extends TestCase {

	public void test01() throws Exception {
		String name = "abcd";

		// �����ӿ�ũ���� �׽�Ʈ�� �����ϱ� ���� �� ��ü�� ������ ���?? -> Put
		// HttpServletRequest request = null;
		Box box = BoxContext.getThread();

		// ����ڰ� �����ϴ� �Է°��� ���� ���??
		// request.setParameter("name", name); -> Put
		box.put("name", name);

		Welcome welcome = new Welcome();
		//welcome.execute();

		// String msg = (String) request.getAttribute("msg"); // Get
		String msg = box.getString("msg"); // Get

		assertEquals("welcome = " + name, msg);
	}

	// ������ : https://xzio.tistory.com/73
	// ������ : https://wonjayk.tistory.com/38
	public void test02_DBConn() throws Exception {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;database=HUMAN;user=Human;password=human";
		try (Connection connection = DriverManager.getConnection(connectionUrl);) {
			// Code here.
			assertNotNull(connection.getSchema());
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
