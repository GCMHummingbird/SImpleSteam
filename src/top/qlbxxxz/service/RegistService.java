package top.qlbxxxz.service;

import java.sql.SQLException;

import top.qlbxxxz.dao.DBHelper;
import top.qlbxxxz.object.User;
import top.qlbxxxz.tools.SimpleMail;
import top.qlbxxxz.tools.SimpleUUID;

public class RegistService {
	private DBHelper dbHelper = new DBHelper();
	
	// 负责开启MySQL链接
	public RegistService() {
		if (dbHelper.isclosed()) {
			dbHelper.startMysql();
		}
	}
	
	// 用于添加用户
	public void addUser(User newUser) {
		// 补全数据
		newUser.setUID(SimpleUUID.getUUID());
		newUser.setActivation(false);
		newUser.setActivationCode(SimpleUUID.getUUID() + SimpleUUID.getUUID());
		// 向数据库中增加用户
		try {
			dbHelper.insert(newUser.getUID(), newUser.getLoginName(),
					newUser.getLoginPWD(), newUser.getEmail(), 
					newUser.isActivation(), newUser.getActivationCode());
		} catch (SQLException e) {
			System.out.println("添加用户失败");
		}
		// 发送激活邮件
		sendActivationEmail(newUser.getEmail(), newUser.getLoginName(), newUser.getActivationCode());
	}
	
	// 发送激活邮件到对应目标
	public void sendActivationEmail(String email, String name, String activationCode) {
		SimpleMail sm = new SimpleMail("smtp.163.com", "qlbxxxz@163.com", "qlbxxxz456");
		// 初始化属性
		sm.setEmailAim(email);
		sm.setEmailType("text/html;charset=UTF-8");
		sm.setEmailTitle("SimpleSteam 激活邮件");
		sm.setCheckPort("465");
		sm.setEmailContent("<h1>" + name + ", SimpleSteam 大家族欢迎您的加入</h1>"
				+ "<a href='http://115.28.61.70/SimpleSteam/RegistServlet?method=activation&activationCode=" + activationCode + "'>点击激活</a>");
		sm.init();
		sm.send();
	}
	
	// 查询email是否存在
	public boolean isExistenceEmail(String email) {
		if (dbHelper.select("t_user", "email", email, "email") != null) {
			return false;
		} else {
			return true;
		}
	}
	// 查询name是否存在
	public boolean isExistenceName(String name) {
		if (dbHelper.select("t_user", "name", name, "name") != null) {
			return false;
		} else {
			return true;
		}
	}
}
