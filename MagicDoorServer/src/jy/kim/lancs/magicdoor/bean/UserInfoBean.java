package jy.kim.lancs.magicdoor.bean;

public class UserInfoBean {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	
	private String eMailAddress;
	private String userType;

	public UserInfoBean() {
	}

	public UserInfoBean(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public UserInfoBean(String userName, String firstName, String lastName, String eMailAddress) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMailAddress = eMailAddress;
	}
	
	public UserInfoBean(String userName, String password, String firstName, String lastName, String eMailAddress) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMailAddress = eMailAddress;
	}
	
	public UserInfoBean(String userName, String password, String firstName, String lastName, String eMailAddress, String userType) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMailAddress = eMailAddress;
		this.userType = userType;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String geteMailAddress() {
		return eMailAddress;
	}
	
	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
