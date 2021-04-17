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
		
		// ��Ȯ�ϰ� 1���� �ƴѰ�� ����
		if (user.size() != 1) {
			box.put("ret", "���̵� ���ų� �н����尡 ��ġ���� �ʽ��ϴ�.");
			return MasterController.execute("loginView");
		}

		Box thisUser = user.getBox();
		
		//SQL������ ������ �н������ �ڹٻ󿡼� �ַ� �����մϴ�.
		if (!PIN.equals(thisUser.s("PIN"))) {
			box.put("ret", "���̵� ���ų� �н����尡 ��ġ���� �ʽ��ϴ�.");

			if (dao.login_02() != 1) {
				throw new Exception();
			}

			return MasterController.execute("loginView");
		}

		// �α��� ���� ���ó���� ����Ʈ���� ���� ���� ���̵忡 �ؼ� 
		int FAIL_CNT = thisUser.getInt("FAIL_CNT");
		if (FAIL_CNT > 5) {
			box.put("ret", "�н����带 5ȸ �̻� �����Ͽ����ϴ�.");
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
