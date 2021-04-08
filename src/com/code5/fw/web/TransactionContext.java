package com.code5.fw.web;

import java.sql.SQLException;

import com.code5.fw.db.Transaction;
import com.code5.fw.db.TransactionSQLServerPool;

/**
 * @author kroch
 *	private 생성자를 가진 유틸 클래스 
 */
public class TransactionContext {
	
	private TransactionContext() {
		
	}
	
	private static ThreadLocal<Transaction> TL = new ThreadLocal<>();
	
	public static Transaction getThread() {
		Transaction transaction = TL.get();
		if(transaction != null) {
			return transaction;
		}else{
			transaction = createDefaultTransaction();
		}
		
		setThread(transaction);
		
		return transaction;
	}
	
	public static void commit() throws SQLException {
		Transaction ta = TL.get();
		if (ta == null) {
			return;
		}
		ta.commit();
	}
	
	public static void rollback() throws SQLException {
		Transaction ta = TL.get();
		if (ta == null) {
			return;
		}
		ta.rollback();
	}
	
	// Master Controller에서 제어하기 떄문에 디폴트 접근제한
	static void setThread(Transaction transaction) {
		TL.set(transaction);
	}
	
	// Master Controller에서 제어하기 떄문에 디폴트 접근제한
	static void removeThread() {
		Transaction ta = TL.get();
		if (ta != null) {
			TL.get().closeConnection();
		}
		TL.remove();
	}
	
	private static Transaction createDefaultTransaction() {
		return new TransactionSQLServerPool();
	}
}
