package test.welcome;
import static org.junit.Assert.assertNotNull;

import com.code5.fw.data.Box;
import com.code5.fw.data.Table;
import com.code5.fw.db.SqlRunner;
import com.code5.fw.db.SqlRunnerB;
import com.code5.fw.web.BoxContext;

import junit.framework.TestCase;

public class DBTest extends TestCase {
	public static void main(String[] args) throws Exception {
		SqlRunner sr = SqlRunner.getSqlRunner();
		Box box = BoxContext.getThread();
		box.put("NAME", "aaa");
		
		Table table = sr.getTable("STAFF");
		
		for (int i = 0 ; i < table.size(); i++ ) {
			box.put("NAME", table.getData("NAME", i));
			int updateCnt = sr.executeSql("STAFF");
			if (updateCnt != 1) {
				throw new Exception();
			}
		}
		
		for (String str : table.getCols()) {
			System.out.println(str);
		}	
	}
}
