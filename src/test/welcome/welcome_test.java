package test.welcome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.code5.biz.welcome.Welcome;
import com.code5.fw.data.Box;
import com.code5.fw.web.BoxContext;

import junit.framework.TestCase;

/**
 * @author kroch JUnit : 테스트 메소드를 찾아서 실행
 *
 *         WAS없이 테스트를 수행하도록 Box로 추상화
 */
public class welcome_test extends TestCase {

	public void test01() throws Exception {
		String name = "abcd";

		// 프레임워크에서 테스트를 제공하기 위해 이 객체를 생성할 방법?? -> Put
		// HttpServletRequest request = null;
		Box box = BoxContext.getThread();

		// 사용자가 전달하는 입력값을 받을 방법??
		// request.setParameter("name", name); -> Put
		box.put("name", name);

		Welcome welcome = new Welcome();
		//welcome.execute();

		// String msg = (String) request.getAttribute("msg"); // Get
		String msg = box.getString("msg"); // Get

		assertEquals("welcome = " + name, msg);
	}

	// 참고문서 : https://xzio.tistory.com/73
	// 참고문서 : https://wonjayk.tistory.com/38
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
