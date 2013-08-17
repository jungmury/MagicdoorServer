package jy.kim.lancs.magicdoor.rest.bean;

public class LogInInfoRespBean {
	private String userName;
	private String userType;
	
	public LogInInfoRespBean(){} //To resolve jackson mapper problem
	
	public String getUserName() {
		return userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String toString() {
		return "loginInfo[userName=" + userName + ", userType="
				+ userType +"]";
	}
}
