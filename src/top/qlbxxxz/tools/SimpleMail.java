package top.qlbxxxz.tools;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 		作    者:	Hummingbird
 * 		摘    要:	本人制作的第一个Java工具类, 目标是简化邮件发送, 需要javax.mail包支持, 请自行导入项目
 * 		版    本:	0.2
 * 		更新日期:	2016-10-07
 * 
 * 		更新内容:	
 *					1.更详细的错误提示
 *					2.更详细的参数提示
 *					3.增加使用方法
 *
 *		使用方法:
 *					1.向构造器传入STMP服务器, 登录名, 授权码(登录密码)						new SimpleMail(emailSrver, loginName, loginPWD)
 *					2.初始化以下属性:
 *						1).验证端口(百度上搜索对应的邮件服务器端口)							setCheckPort(checkPort)
 *						2).邮件标题															setEmailTitle(emailTitle)
 *						3).邮件内容															setEmailContent(emailContent)
 *						4).邮件类型															setEmailType(emailType)
 *						5).邮件目标															setEmailAim(emailAim)
 *					3.执行对象的init方法, 完成发送准备
 *					3.执行对象的send方法, 完成发送
 */
public class SimpleMail {
	// 登录所需
	private String emailServer;																// 邮件服务器
	private String loginName;																// 登录名
	private String loginPWD;																// 登录授权密码
	private String checkPort;																// 验证端口
	// 发送所需
	private String emailTitle;																// 邮件标题
	private String emailContent;															// 邮件内容
	private String emailType;																// 邮件类型
	private String emailAim;																// 邮件目标
	private boolean isInit = false;															// 是否初始化
	// 会话所需
	private MimeMessage message;															// 消息
	private Session session;																// 回话

	/**
	 * 简易邮件构造器
	 * @param mailServer	邮件stmp服务器
	 * @param loginName		登录名
	 * @param loginPWD		授权码
	 */
	public SimpleMail(String emailServer, String loginName, String loginPWD) {
		this.emailServer = emailServer;
		this.loginName = loginName;
		this.loginPWD = loginPWD;
	}
	
	/**
	 * 该方法用于完成初始化
	 */
	public void init() {
		// 判断发送邮件属性是否为空
		if (!check()) {
			return;
		}
		
		Properties props = new Properties();												// 校验所需键值对
		// 添加键值对
		props.put("mail.smtp.host", emailServer);											// 服务器配置
		props.put("mail.smtp.port", checkPort);												// 校验端口
		props.put("mail.smtp.auth", "true");												// 身份验证开启
		props.put("mail.smtp.starttls.enable", "true");										// TLS开启
		props.put("mail.smtp.EnableSSL.enable","true");										// SSL开启
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");		// SSL配置
		
		session = Session.getDefaultInstance(props, null);									// 获取会话
		message = new MimeMessage(session);													// 获取消息
		
		// 开始处理发送和接收
		try {
			// 处理发件人和收件人
			InternetAddress from = new InternetAddress(loginName);							// 生成发件人
			InternetAddress to = new InternetAddress(emailAim);								// 生成收件人
			// 消息处理
			message.setFrom(from);															// 置消息发件人
			message.setRecipient(Message.RecipientType.TO, to);								// 置消息收件人
			message.setSubject(emailTitle);													// 置邮件标题
			message.setContent(emailContent, emailType);									// 置邮件内容与类型
			message.saveChanges();															// 保存邮件信息
		} catch (Exception e) {
			e.printStackTrace();
		}							
		
		isInit = true;
	}
	
	/**
	 * 该方法用于发送邮件
	 */
	public void send() {
		// 校验是否初始化
		if (!isInit) {
			System.out.println("必要属性未完成初始化!");
			
			return;
		}

		// 连接, 发送, 关闭
		try {
			Transport transport = session.getTransport("smtp");								// 设置连接协议
			transport.connect(emailServer, loginName, loginPWD);							// 开始连接
			transport.sendMessage(message, message.getAllRecipients());						// 发送
			transport.close();																// 关闭连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 该方法用于验证属性是否完成
	 * @return 验证是否通过
	 */
	private boolean check() {
		boolean checkPortIsNull = false;
		boolean emailTitleIsNull = false;
		boolean emailTypeIsNull = false;
		boolean emailContentIsNull = false;
		boolean emailAimIsNull = false;
		
		// 判断各属性是否为空
		if (checkPort == null) checkPortIsNull = true;
		if (emailTitle == null) emailTitleIsNull = true;
		if (emailType == null) emailTypeIsNull = true;
		if (emailContent == null) emailContentIsNull = true;
		if (emailAim == null) emailAimIsNull = true;
		
		// 只要有一项为空则不能通过非空校验
		if (checkPortIsNull || emailTitleIsNull ||  emailTypeIsNull || emailContentIsNull || emailAimIsNull) {
			System.out.println("下列属性未初始化:请通过Setter方法对其进行初始化");
			
			if (checkPortIsNull) System.err.println("checkPort(发送端口):setCheckPort()");
			if (emailTitleIsNull) System.err.println("emailTitle(邮件标题):setEmailTitle()");;
			if (emailTypeIsNull) System.err.println("emailType(邮件类型):setEmailType()");
			if (emailContentIsNull) System.err.println("emailC(邮件内容):setEmailContent()");
			if (emailAimIsNull) System.err.println("emailAim(邮件接收者):setEmailAim()");
			
			System.out.println("--------------End--------------");
			
			return false;
		}
		else {
			return true;	
		}
	}
	
	
	
	/**
	 * @return 邮件接收者
	 */
	public String getEmailAim() {
		return emailAim;
	}
	/**
	 * @param emailAim 邮件接收者
	 */
	public void setEmailAim(String emailAim) {
		this.emailAim = emailAim;
	}
	/**
	 * @return 邮件服务器
	 */
	public String getMailServer() {
		return emailServer;
	}
	/**
	 * @param mailServer 邮件服务器
	 */
	public void setMailServer(String mailServer) {
		this.emailServer = mailServer;
	}
	/**
	 * @return 登录名
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName 登录名
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return 授权码获取
	 */
	public String getLoginPWD() {
		return loginPWD;
	}
	/**
	 * @param loginPWD 登录授权码
	 */
	public void setLoginPWD(String loginPWD) {
		this.loginPWD = loginPWD;
	}
	/**
	 * @return 服务器验证端口
	 */
	public String getCheckPort() {
		return checkPort;
	}
	/**
	 * @param checkPort 服务器验证端口
	 */
	public void setCheckPort(String checkPort) {
		this.checkPort = checkPort;
	}
	/**
	 * @return 邮件标题
	 */
	public String getEmailTitle() {
		return emailTitle;
	}
	/**
	 * @param emailTitle 邮件标题
	 */
	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}
	/**
	 * @return 邮件内容
	 */
	public String getEmailContent() {
		return emailContent;
	}
	/**
	 * @param emailContent 邮件内容
	 */
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	/**
	 * @return 邮件类型
	 */
	public String getEmailType() {
		return emailType;
	}
	/**
	 * @param emailType 邮件类型
	 * 一共两种类型:	text/plain;charset=GBK
	 * 				text/html;charset=UTF-8;
	 */
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	
	@Override
	public String toString() {
		return "SimpleMail [emailServer=" + emailServer + ", loginName="
				+ loginName + ", loginPWD=" + loginPWD + ", checkPort="
				+ checkPort + ", emailTitle=" + emailTitle + ", emailContent=" + emailContent
				+ ", emailType=" + emailType + ", emailAim=" + emailAim
				+ ", isInit=" + isInit + ", message=" + message + ", session="
				+ session + "]";
	}
}
