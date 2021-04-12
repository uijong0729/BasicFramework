package com.code5.biz.login;

import com.code5.biz.emp.Emp001;
import com.code5.fw.data.Box;
import com.code5.fw.web.BizController;
import com.code5.fw.web.BoxContext;

public class Login implements BizController {
	
	
	public String login() throws Exception {
		Box box = BoxContext.getThread();
		boolean isLogin = box.getBoolean("isLogin");
		
		if (isLogin) {
			Emp001 emp001 = new Emp001();
			return emp001.emp00110();
		}
		
		return loginView();
	}
	
	public String loginView() throws Exception {
		return "loginView";
	}
}
