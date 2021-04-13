package com.code5.biz.login;

import com.code5.biz.emp.Emp001;
import com.code5.fw.data.Box;
import com.code5.fw.web.BizController;
import com.code5.fw.web.BoxContext;
import com.code5.fw.web.MasterController;

public class Login implements BizController {
	
	
	public String login() throws Exception {
		Box box = BoxContext.getThread();
		boolean isLogin = box.getBoolean("isLogin");
		
		if (isLogin) {
			// IoC : 객체의 생성/사용/마무리를 개발자 코드에서 수행하지 않음
			return MasterController.execute("emp00110");
		}
		
		return loginView();
	}
	
	public String loginView() throws Exception {
		return "loginView";
	}
}
