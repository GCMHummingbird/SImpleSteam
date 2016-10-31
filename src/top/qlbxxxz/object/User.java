package top.qlbxxxz.object;

public class User {
	// 本身数据
	private String UID;						// 用户ID
	private String loginName;				// 登录名
	private String loginPWD;					// 登录密码
	private String email;					// 邮箱
	private String activationCode;			// 用户激活码
	private boolean isActivation;			// 用户激活状态

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
