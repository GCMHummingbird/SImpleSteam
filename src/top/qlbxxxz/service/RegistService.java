package top.qlbxxxz.service;

import java.sql.SQLException;

import top.qlbxxxz.dao.DBHelper;
import top.qlbxxxz.object.User;
import top.qlbxxxz.tools.SimpleMail;
import top.qlbxxxz.tools.SimpleUUID;

public class RegistService {
	private DBHelper dbHelper = new DBHelper();
	
	// ������MySQL����
	public RegistService() {
		if (dbHelper.isclosed()) {
			dbHelper.startMysql();
		}
	}
	
	// ��������û�
	public void addUser(User newUser) {
		// ��ȫ����
		newUser.setUID(SimpleUUID.getUUID());
		newUser.setActivation(false);
		newUser.setActivationCode(SimpleUUID.getUUID() + SimpleUUID.getUUID());
		// �����ݿ��������û�
		try {
			dbHelper.insert(newUser.getUID(), newUser.getLoginName(),
					newUser.getLoginPWD(), newUser.getEmail(), 
					newUser.isActivation(), newUser.getActivationCode());
		} catch (SQLException e) {
			System.out.println("����û�ʧ��");
		}
		// ���ͼ����ʼ�
		sendActivationEmail(newUser.getEmail(), newUser.getLoginName(), newUser.getActivationCode());
	}
	
	// ���ͼ����ʼ�����ӦĿ��
	public void sendActivationEmail(String email, String name, String activationCode) {
		SimpleMail sm = new SimpleMail("smtp.163.com", "qlbxxxz@163.com", "qlbxxxz456");
		// ��ʼ������
		sm.setEmailAim(email);
		sm.setEmailType("text/html;charset=UTF-8");
		sm.setEmailTitle("SimpleSteam �����ʼ�");
		sm.setCheckPort("465");
		sm.setEmailContent("<h1>" + name + ", SimpleSteam ����延ӭ���ļ���</h1>"
				+ "<a href='http://115.28.61.70/SimpleSteam/RegistServlet?method=activation&activationCode=" + activationCode + "'>�������</a>");
		sm.init();
		sm.send();
	}
	
	// ��ѯemail�Ƿ����
	public boolean isExistenceEmail(String email) {
		if (dbHelper.select("t_user", "email", email, "email") != null) {
			return false;
		} else {
			return true;
		}
	}
	// ��ѯname�Ƿ����
	public boolean isExistenceName(String name) {
		if (dbHelper.select("t_user", "name", name, "name") != null) {
			return false;
		} else {
			return true;
		}
	}
}
