package com.code5.biz.login;

import com.code5.fw.data.Box;
import com.code5.fw.data.SessionB;
import com.code5.fw.data.Table;
import com.code5.fw.web.BizController;
import com.code5.fw.web.BoxContext;
import com.code5.fw.web.MasterController;

/**
 * @author zero
 *
 */
/**
 * @author kroch
 *		http://localhost:18080/waf/loginView
 */
public class Login implements BizController {

	/**
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {

		Box box = BoxContext.getThread();

		String ID = box.s("USR_ID");
		String PIN = box.s("PIN");

		LoginD dao = LoginD.getLoginD();

		Table user = dao.login_01();
		
		// 명확하게 1줄이 아닌경우 에러
		if (user.size() != 1) {
			box.put("ret", "아이디가 없거나 패스워드가 일치하지 않습니다.");
			return MasterController.execute("loginView");
		}

		Box thisUser = user.getBox();
		
		//SQL인젝션 때문에 패스워드는 자바상에서 주로 인증합니다.
		if (!PIN.equals(thisUser.s("PIN"))) {
			box.put("ret", "아이디가 없거나 패스워드가 일치하지 않습니다.");

			if (dao.login_02() != 1) {
				throw new Exception();
			}

			return MasterController.execute("loginView");
		}

		// 로그인 실패 잠금처리는 소프트웨어 개발 보안 가이드에 준수 
		int FAIL_CNT = thisUser.getInt("FAIL_CNT");
		if (FAIL_CNT > 5) {
			box.put("ret", "패스워드를 5회 이상 실패하였습니다.");
			return MasterController.execute("loginView");
		}

		if (dao.login_03() != 1) {
			throw new Exception();

		}

		String AUTH = thisUser.s("AUTH");
		String IP = box.s(Box.KEY_REMOTE_ADDR);

		SessionB sessionB = new SessionB(ID, AUTH, IP);
		box.setSessionB(sessionB);

		return MasterController.execute("emp00110");
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {

		BoxContext.getThread().setSessionB(null);
		return loginView();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String loginView() throws Exception {
		return "loginView";
	}

}
