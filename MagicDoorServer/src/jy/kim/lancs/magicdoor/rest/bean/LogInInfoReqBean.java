package jy.kim.lancs.magicdoor.rest.bean;

public class LogInInfoReqBean {
	private String userName;
	private String password;

	public LogInInfoReqBean() {
	};

	// to resolve JsonMappingException : No suitable constructor found for---

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
