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
	 * 1. conn���� -> createConnection, ���� ��ü ����
	 * 2. SQL ��ɿ� �ʿ��� �ڿ� ���� -> PrepareStatement, Statement, ResultSet
	 * 3. conn�ڿ� �ݳ� -> SQL��ɿ� ���� �ڿ� �ݳ�, close
	 * 4. Ʈ����� ���� ����
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
	 * �����ڰ� ��������� Connection�� �� �� ������ ���� -> ���� ��ü ������ ����
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
		
		//ResultSet�� ��ȯ
		this.close();
		this.conn.commit();
	} 
	
	public void rollback() throws SQLException {
		if (this.conn == null) {
			return;
		}
		
		//ResultSet�� ��ȯ
		this.close();
		this.conn.rollback();
	}
	
	// �̸� �غ�� ������ Bind������ ���� : ���� SQL (DBMS���忡�� PreparedStatement�� �̿��ϴ°��� ����)
	PreparedStatement prepareStatement(String SQL) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement ps = conn.prepareStatement(SQL);
		stList.add(ps);
		return ps;
	}
	
	// ������ ���� �׋��׋� �ٸ� SQL�� ���� : ���� SQL (DBMS���忡�� ó���� ����)
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
