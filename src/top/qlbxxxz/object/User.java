package top.qlbxxxz.object;

public class User {
	// ��������
	private String UID;						// �û�ID
	private String loginName;				// ��¼��
	private String loginPWD;					// ��¼����
	private String email;					// ����
	private String activationCode;			// �û�������
	private boolean isActivation;			// �û�����״̬

	// UID
	public String getUID() {
		return UID;
	}
	public void setUID(String UID) {
		this.UID = UID;
	}
	// loginName
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	// loginPWD
	public String getLoginPWD() {
		return loginPWD;
	}
	public void setLoginPWD(String loginPWD) {
		this.loginPWD = loginPWD;
	}
	// email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	// isActivation
	public boolean isActivation() {
		return isActivation;
	}
	public void setActivation(boolean isActivation) {
		this.isActivation = isActivation;
	}
	// activationCode
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
}
