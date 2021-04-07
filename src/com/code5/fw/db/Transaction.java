package com.code5.fw.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Transaction {
	public void Connection() {
        String connectionUrl = "";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            // Code here.
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
