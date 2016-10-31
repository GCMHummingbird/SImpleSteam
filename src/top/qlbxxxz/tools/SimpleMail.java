package top.qlbxxxz.tools;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 		��    ��:	Hummingbird
 * 		ժ    Ҫ:	���������ĵ�һ��Java������, Ŀ���Ǽ��ʼ�����, ��Ҫjavax.mail��֧��, �����е�����Ŀ
 * 		��    ��:	0.2
 * 		��������:	2016-10-07
 * 
 * 		��������:	
 *					1.����ϸ�Ĵ�����ʾ
 *					2.����ϸ�Ĳ�����ʾ
 *					3.����ʹ�÷���
 *
 *		ʹ�÷���:
 *					1.����������STMP������, ��¼��, ��Ȩ��(��¼����)						new SimpleMail(emailSrver, loginName, loginPWD)
 *					2.��ʼ����������:
 *						1).��֤�˿�(�ٶ���������Ӧ���ʼ��������˿�)							setCheckPort(checkPort)
 *						2).�ʼ�����															setEmailTitle(emailTitle)
 *						3).�ʼ�����															setEmailContent(emailContent)
 *						4).�ʼ�����															setEmailType(emailType)
 *						5).�ʼ�Ŀ��															setEmailAim(emailAim)
 *					3.ִ�ж����init����, ��ɷ���׼��
 *					3.ִ�ж����send����, ��ɷ���
 */
public class SimpleMail {
	// ��¼����
	private String emailServer;																// �ʼ�������
	private String loginName;																// ��¼��
	private String loginPWD;																// ��¼��Ȩ����
	private String checkPort;																// ��֤�˿�
	// ��������
	private String emailTitle;																// �ʼ�����
	private String emailContent;															// �ʼ�����
	private String emailType;																// �ʼ�����
	private String emailAim;																// �ʼ�Ŀ��
	private boolean isInit = false;															// �Ƿ��ʼ��
	// �Ự����
	private MimeMessage message;															// ��Ϣ
	private Session session;																// �ػ�

	/**
	 * �����ʼ�������
	 * @param mailServer	�ʼ�stmp������
	 * @param loginName		��¼��
	 * @param loginPWD		��Ȩ��
	 */
	public SimpleMail(String emailServer, String loginName, String loginPWD) {
		this.emailServer = emailServer;
		this.loginName = loginName;
		this.loginPWD = loginPWD;
	}
	
	/**
	 * �÷���������ɳ�ʼ��
	 */
	public void init() {
		// �жϷ����ʼ������Ƿ�Ϊ��
		if (!check()) {
			return;
		}
		
		Properties props = new Properties();												// У�������ֵ��
		// ��Ӽ�ֵ��
		props.put("mail.smtp.host", emailServer);											// ����������
		props.put("mail.smtp.port", checkPort);												// У��˿�
		props.put("mail.smtp.auth", "true");												// �����֤����
		props.put("mail.smtp.starttls.enable", "true");										// TLS����
		props.put("mail.smtp.EnableSSL.enable","true");										// SSL����
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");		// SSL����
		
		session = Session.getDefaultInstance(props, null);									// ��ȡ�Ự
		message = new MimeMessage(session);													// ��ȡ��Ϣ
		
		// ��ʼ�����ͺͽ���
		try {
			// �������˺��ռ���
			InternetAddress from = new InternetAddress(loginName);							// ���ɷ�����
			InternetAddress to = new InternetAddress(emailAim);								// �����ռ���
			// ��Ϣ����
			message.setFrom(from);															// ����Ϣ������
			message.setRecipient(Message.RecipientType.TO, to);								// ����Ϣ�ռ���
			message.setSubject(emailTitle);													// ���ʼ�����
			message.setContent(emailContent, emailType);									// ���ʼ�����������
			message.saveChanges();															// �����ʼ���Ϣ
		} catch (Exception e) {
			e.printStackTrace();
		}							
		
		isInit = true;
	}
	
	/**
	 * �÷������ڷ����ʼ�
	 */
	public void send() {
		// У���Ƿ��ʼ��
		if (!isInit) {
			System.out.println("��Ҫ����δ��ɳ�ʼ��!");
			
			return;
		}

		// ����, ����, �ر�
		try {
			Transport transport = session.getTransport("smtp");								// ��������Э��
			transport.connect(emailServer, loginName, loginPWD);							// ��ʼ����
			transport.sendMessage(message, message.getAllRecipients());						// ����
			transport.close();																// �ر�����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �÷���������֤�����Ƿ����
	 * @return ��֤�Ƿ�ͨ��
	 */
	private boolean check() {
		boolean checkPortIsNull = false;
		boolean emailTitleIsNull = false;
		boolean emailTypeIsNull = false;
		boolean emailContentIsNull = false;
		boolean emailAimIsNull = false;
		
		// �жϸ������Ƿ�Ϊ��
		if (checkPort == null) checkPortIsNull = true;
		if (emailTitle == null) emailTitleIsNull = true;
		if (emailType == null) emailTypeIsNull = true;
		if (emailContent == null) emailContentIsNull = true;
		if (emailAim == null) emailAimIsNull = true;
		
		// ֻҪ��һ��Ϊ������ͨ���ǿ�У��
		if (checkPortIsNull || emailTitleIsNull ||  emailTypeIsNull || emailContentIsNull || emailAimIsNull) {
			System.out.println("��������δ��ʼ��:��ͨ��Setter����������г�ʼ��");
			
			if (checkPortIsNull) System.err.println("checkPort(���Ͷ˿�):setCheckPort()");
			if (emailTitleIsNull) System.err.println("emailTitle(�ʼ�����):setEmailTitle()");;
			if (emailTypeIsNull) System.err.println("emailType(�ʼ�����):setEmailType()");
			if (emailContentIsNull) System.err.println("emailC(�ʼ�����):setEmailContent()");
			if (emailAimIsNull) System.err.println("emailAim(�ʼ�������):setEmailAim()");
			
			System.out.println("--------------End--------------");
			
			return false;
		}
		else {
			return true;	
		}
	}
	
	
	
	/**
	 * @return �ʼ�������
	 */
	public String getEmailAim() {
		return emailAim;
	}
	/**
	 * @param emailAim �ʼ�������
	 */
	public void setEmailAim(String emailAim) {
		this.emailAim = emailAim;
	}
	/**
	 * @return �ʼ�������
	 */
	public String getMailServer() {
		return emailServer;
	}
	/**
	 * @param mailServer �ʼ�������
	 */
	public void setMailServer(String mailServer) {
		this.emailServer = mailServer;
	}
	/**
	 * @return ��¼��
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName ��¼��
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return ��Ȩ���ȡ
	 */
	public String getLoginPWD() {
		return loginPWD;
	}
	/**
	 * @param loginPWD ��¼��Ȩ��
	 */
	public void setLoginPWD(String loginPWD) {
		this.loginPWD = loginPWD;
	}
	/**
	 * @return ��������֤�˿�
	 */
	public String getCheckPort() {
		return checkPort;
	}
	/**
	 * @param checkPort ��������֤�˿�
	 */
	public void setCheckPort(String checkPort) {
		this.checkPort = checkPort;
	}
	/**
	 * @return �ʼ�����
	 */
	public String getEmailTitle() {
		return emailTitle;
	}
	/**
	 * @param emailTitle �ʼ�����
	 */
	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}
	/**
	 * @return �ʼ�����
	 */
	public String getEmailContent() {
		return emailContent;
	}
	/**
	 * @param emailContent �ʼ�����
	 */
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	/**
	 * @return �ʼ�����
	 */
	public String getEmailType() {
		return emailType;
	}
	/**
	 * @param emailType �ʼ�����
	 * һ����������:	text/plain;charset=GBK
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
