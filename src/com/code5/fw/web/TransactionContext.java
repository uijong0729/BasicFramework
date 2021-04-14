package com.code5.fw.web;

import java.sql.SQLException;

import com.code5.fw.db.Transaction;
import com.code5.fw.db.TransactionSQLServerPool;

/**
 * @author zero
 *
 */
public class TransactionContext {

	// ��ƿ Ŭ����, private ������
	// AOP -> ThreadLocal
	// createDefaultTransaction -> Transaction_SQLITE_JDBC
	// WAS ȯ�濡�� Transaction_SQLITE_POOL ���
	// �����ڰ� ���� ���� commit �� rollback

	/**
	 * 
	 */
	private TransactionContext() {

	}

	/**
	 * 
	 */
	private static ThreadLocal<Transaction> TL = new ThreadLocal<Transaction>();

	/**
	 * @return
	 * 
	 * 
	 */
	public static Transaction getThread() {
		Transaction transaction = TL.get();
		if (transaction != null) {
			return transaction;

		}

		transaction = createDefaultTransaction();
		setThread(transaction);

		return transaction;
	}

	/**
	 * @param transaction
	 * 
	 * 
	 */
	static void setThread(Transaction transaction) {
		TL.set(transaction);
	}

	/**
	 *
	 */
	static void removeThread() {
		Transaction transaction = TL.get();
		if (transaction != null) {
			TL.get().closeConnection();
		}

		TL.remove();
	}

	/**
	 * @return
	 * 
	 *         TODO [2]
	 */
	private static Transaction createDefaultTransaction() {
		return new TransactionSQLServerPool();
	}

	/**
	 * @throws Exception
	 */
	public static void commit() throws SQLException {

		Transaction transaction = TL.get();
		if (transaction == null) {
			return;
		}

		transaction.commit();
	}

	/**
	 * 
	 */
	public static void rollback() throws SQLException {

		Transaction transaction = TL.get();
		if (transaction == null) {
			return;
		}

		transaction.rollback();

	}
}
