package com.code5.fw.web;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.code5.biz.welcome.Welcome;
import com.code5.fw.data.Box;
import com.code5.fw.data.BoxHttp;
import com.code5.fw.db.Transaction;
import com.code5.fw.db.TransactionSQLServerPool;

public class MasterController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	/**
	 * TransactionContext�� ��� Transaction ��ü�� ����Ͻ� ������ ����
	 * commit�� roll-back���� ����
	 * 
	 * conn ��ü �����ֱ� Ȯ��
	 * new conn()
	 * conn.commit()
	 * conn.rollback()
	 * conn.close()
	 * 
	 *	@Param HttpServletRequest request : was���� �Ѱ��� ��ü
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		
		Box box = new BoxHttp(request);
		BoxContext.setThread(box);
		
		Transaction transaction = new TransactionSQLServerPool();
		TransactionContext.setThread(transaction);
		
		try {
			Welcome welcome = new Welcome();
			
			// execute �� ó���� �ϳ��� Ʈ����� 
			String jsp = welcome.execute();
			
			// execute ������ Ŀ��
			TransactionContext.commit();
			
			// was���� ����� request, response�� �𵨿� �������ִ� ����
			RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			try {
				// execute ���н� �ѹ�
				TransactionContext.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new ServletException(e1);
			}
		}finally {
			TransactionContext.removeThread();
			BoxContext.removeThread();
		}
	}
}
