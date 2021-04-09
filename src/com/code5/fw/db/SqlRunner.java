package com.code5.fw.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.code5.fw.data.Box;
import com.code5.fw.data.Table;
import com.code5.fw.web.BoxContext;
import com.code5.fw.web.TransactionContext;

public class SqlRunner {
	private static SqlRunner sql = new SqlRunner();
	private SqlRunner() {}
	private final static String NON_META_QUESTION_MARK = "\\?";
	
	public static SqlRunner getSqlRunner() {
		return sql;
	}
	
	public Table getTable(Transaction trans, Box box, String key) throws SQLException {
		SqlRunnerB sqlRunnerB = getSqlRunnerB(trans, key);
		PreparedStatement ps = trans.prepareStatement(sqlRunnerB.sql);
		
		String exeSql = sqlRunnerB.sql;
		for (int i = 0 ; i < sqlRunnerB.param.size(); i++) {
			String thiskey = sqlRunnerB.param.get(i);
			String thisData = box.getString(thiskey);
			ps.setString(i+1, thisData);
			
			exeSql = exeSql.replaceFirst(NON_META_QUESTION_MARK, "'" + thisData + "'");
		}
		System.out.println(exeSql);
		ResultSet rs = trans.getResultSet(ps);
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		String[] cols = new String[columnCount];
		
		for (int i = 0 ; i < cols.length ; i++) {
			cols[i] = metaData.getColumnName(i+1);
		}
		Table table = new Table(cols);
		
		while (rs.next()) {
			String[] recode = new String[columnCount];
			for (int i = 0; i < cols.length ; i++) {
				recode[i] = rs.getString(cols[i]);
			}
			
			boolean isAddRecode = table.addRecode(recode);
			if (!isAddRecode) {
				break;
			}
		}
		trans.close();
		return table;
	}
	
	SqlRunnerB getSqlRunnerB(Transaction trans, String key) throws SQLException {
		String sql = getSQL(trans, key);
		SqlRunnerB sqlrunnerb = getSqlRunnerB(sql);
		sqlrunnerb.key = key;
		return sqlrunnerb;
	}
	
	String getSQL(Transaction trans, String key) throws SQLException{
		// SQL을 저장하는 테이블 
		PreparedStatement ps = trans.prepareStatement("SELECT SQL FROM FW_SQL WHERE KEY = ?");
		ps.setString(1, key);
		ResultSet rs = trans.getResultSet(ps);
		if (!rs.next()) {
			throw new RuntimeException();
		}
		String sql = rs.getString("SQL");
		trans.close();
		return sql;
	}
	
	SqlRunnerB getSqlRunnerB(String sql) throws SQLException {
		// 바인드 변수의 나열 
		ArrayList<String> param = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		
		// 무한루프를 회피하고 1000번까지만 돌 수 있게
		int fromIndex = -1;
		for(int i = 0; i < 1000 ; i++) {
			//바인드 변수 처리 부분
			int sp = sql.indexOf("[", fromIndex);
			if (sp == -1) {
				sb.append(sql.substring(fromIndex + 1));
				break;
			}
			int ep = sql.indexOf("]", sp +1);
			param.add(sql.substring(sp + 1, ep));
			
			param.add(sql.substring(sp+1, ep));
			sb.append(sql.substring(fromIndex + 1, sp) + "?");
			fromIndex = ep;
		}
		SqlRunnerB sqlRunnerB = new SqlRunnerB();
		sqlRunnerB.param = param;
		sqlRunnerB.sql = sb.toString();
		sqlRunnerB.sqlOrg = sql;
		return sqlRunnerB;
	}
	
	public Table getTable(String key) throws SQLException {
		Transaction tran = TransactionContext.getThread();
		Box box = BoxContext.getThread();
		return getTable(tran, box, key);
	}
	
	public Table getTable(String key, Box box) throws SQLException {
		Transaction tran = TransactionContext.getThread();
		return getTable(tran, box, key);
	}
	
	public int executeSql(Transaction transaction, Box box, String key) throws SQLException {
		SqlRunnerB sqlRunnerB = getSqlRunnerB(transaction, key);
		transaction.setAutoCommitFalse();
		PreparedStatement ps = transaction.prepareStatement(sqlRunnerB.sql);
		
		String exeSql = sqlRunnerB.sql;
		for (int i = 0 ; i < sqlRunnerB.param.size(); i++) {
			String thisKey = sqlRunnerB.param.get(i);
			String thisData = box.getString(thisKey);
			ps.setString(i+1, thisData);
			exeSql = exeSql.replaceFirst(NON_META_QUESTION_MARK, "'" + thisData + "'");
		}
		int i = ps.executeUpdate();
		transaction.close();
		return i;
	}
	
	public int executeSql(String key) throws SQLException {
		Transaction tran = TransactionContext.getThread();
		Box box = BoxContext.getThread();
		return executeSql(tran, box, key);
	}
	
	public int executeSql(String key, Box box) throws SQLException {
		Transaction tran = TransactionContext.getThread();
		return executeSql(tran, box, key);
	}

}
