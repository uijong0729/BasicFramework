package com.code5.fw.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionSQLServerPool extends Transaction {

	@Override
	protected Connection createConnection() throws SQLException {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;database=HUMAN;user=Human;password=human";		
		Connection connection = DriverManager.getConnection(connectionUrl);
		
		//conn.setAutoCommit(false);
		
		return connection;
	}
}
