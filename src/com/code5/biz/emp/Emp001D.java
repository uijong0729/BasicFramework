package com.code5.biz.emp;

import java.sql.SQLException;

import com.code5.fw.data.Table;
import com.code5.fw.db.SqlRunner;

/**
 * @author kroch
 *		DataObject
 */
public class Emp001D {

	/**
	 * 
	 */
	private SqlRunner sql = SqlRunner.getSqlRunner();

	/**
	 * @return
	 * @throws SQLException
	 * 
	 * 접근제어자 default로하면 해당 패키지에서만 사용되기 떄문에 테스트 범위도 줄어든다.
	 */
	Table emp00101() throws SQLException {
		return sql.getTable("EMP001D_01");
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	int emp00102() throws SQLException {
		return sql.executeSql("EMP001D_02");
	}

}
