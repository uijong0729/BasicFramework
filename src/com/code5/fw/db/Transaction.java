package com.code5.fw.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

public abstract class Transaction {
	
	private Connection conn = null;
	private boolean setAutoCommit = false;
	private ArrayList<ResultSet> rsList = new ArrayList<>();
	private ArrayList<Statement> stList = new ArrayList<>();
	
	/**
	 * 1. conn생성 -> createConnection, 늦은 객체 생성
	 * 2. SQL 기능에 필요한 자원 생성 -> PrepareStatement, Statement, ResultSet
	 * 3. conn자원 반납 -> SQL기능에 사용된 자원 반납, close
	 * 4. 트랜잭션 상태 제어
	 * 
	 * @return
	 * @throws SQLException
	 */
	protected abstract Connection createConnection() throws SQLException;
	
	public void closeConnection() {
		try {
			if (conn == null) {
				return;
			}
			this.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 개발자가 명시적으로 Connection을 쓸 수 없도록 감춤 -> 늦은 객체 생성을 위해
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException{
		if (this.conn == null) {
			this.conn = createConnection();
		}
		return conn;
	}
	
	public void commit() throws SQLException {
		if (this.conn == null) {
			return;
		}
		
		//ResultSet을 반환
		this.close();
		this.conn.commit();
	} 
	
	public void rollback() throws SQLException {
		if (this.conn == null) {
			return;
		}
		
		//ResultSet을 반환
		this.close();
		this.conn.rollback();
	}
	
	// 미리 준비된 구문에 Bind변수만 전달 : 정적 SQL (DBMS입장에선 PreparedStatement를 이용하는것이 빠름)
	PreparedStatement prepareStatement(String SQL) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(SQL);
		stList.add(ps);
		return ps;
	}
	
	// 로직에 따라 그떄그떄 다른 SQL을 실행 : 동적 SQL (DBMS입장에선 처리가 느림)
	Statement createStatement() throws SQLException {
		Connection conn = getConnection();
		Statement st = conn.createStatement();
		stList.add(st);
		return st;
	}
	
	/**
	 * @param ps
	 * @return
	 * @throws SQLException
	 */
	ResultSet getResultSet(PreparedStatement ps) throws SQLException {
		ResultSet rs = ps.executeQuery();
		rsList.add(rs);
		return rs;

	}
	
	void close() {
		for (ResultSet rs : rsList) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		rsList.clear();
		
		for (Statement st : stList) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		stList.clear();
	}
	
	public void setAutoCommitFalse() throws SQLException{
		if (setAutoCommit) {
			return;
		}
		
		setAutoCommit = true;
		conn.setAutoCommit(false);
	}
	
}
