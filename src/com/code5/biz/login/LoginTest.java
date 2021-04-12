package com.code5.biz.login;

import com.code5.fw.data.Box;
import com.code5.fw.web.BoxContext;

import junit.framework.TestCase;

public class LoginTest extends TestCase{
	
	public void test_login_true() throws Exception {
		Box box = BoxContext.getThread();
		box.put("isLogin", true);
		
		Login login = new Login();
		login.login();
	} 
	
	public void test_login_false() throws Exception {
		Box box = BoxContext.getThread();
		box.put("isLogin", false);
		
		Login login = new Login();
		login.login();
	} 
}
