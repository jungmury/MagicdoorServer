package jy.kim.lancs.magicdoor.login.dao;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jy.kim.lancs.magicdoor.bean.UserInfoBean;

public class MagicDoorLoginSystemDbDao {
	private Connection con;

	public MagicDoorLoginSystemDbDao(Connection con) {
		this.con = con;
	}

	
	//Login
	public UserInfoBean selectLoginInfo(UserInfoBean loginInfo) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserInfoBean loggedInUserInfo = null;
		String sql = "SELECT user_name, first_name, user_type FROM tbl_users WHERE user_name= ? AND password = ? AND user_type = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginInfo.getUserName());
			pstmt.setString(2, loginInfo.getPassword());
			// lecturer log in condition
			pstmt.setString(3, "lecturer");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				loggedInUserInfo = new UserInfoBean();
				loggedInUserInfo.setUserName(rs.getString("user_name"));
				loggedInUserInfo.setFirstName(rs.getString("first_name"));
				loggedInUserInfo.setUserType(rs.getString("user_type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return loggedInUserInfo;
	}
	
	
	//
	private UserInfoBean selectLoginInfo(String userName) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserInfoBean loggedInUserInfo = null;
		String sql = "SELECT user_name, first_name, user_type FROM tbl_users WHERE user_name= ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				loggedInUserInfo = new UserInfoBean();
				loggedInUserInfo.setUserName(rs.getString("user_name"));
				loggedInUserInfo.setFirstName(rs.getString("first_name"));
				loggedInUserInfo.setUserType(rs.getString("user_type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return loggedInUserInfo;
	}

	
	//Register User
	public UserInfoBean insertNewUser(UserInfoBean signUpInfo) {
		// TODO Auto-generated method stub
		int count = 0;

		PreparedStatement pstmt = null;
		UserInfoBean loggedInUserInfo = null;

		String sql = "INSERT INTO tbl_users(user_name, password, first_name, last_name, email_address, user_type) VALUES (?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, signUpInfo.getUserName());
			pstmt.setString(2, signUpInfo.getPassword());
			pstmt.setString(3, signUpInfo.getFirstName());
			pstmt.setString(4, signUpInfo.getLastName());
			pstmt.setString(5, signUpInfo.geteMailAddress());
			pstmt.setString(6, signUpInfo.getUserType());

			count = pstmt.executeUpdate();
			if(count>0){
				
				//return logged in information
				loggedInUserInfo = new UserInfoBean();
				loggedInUserInfo.setUserName(signUpInfo.getUserName());
				loggedInUserInfo.setFirstName(signUpInfo.getFirstName());
				loggedInUserInfo.setUserType(signUpInfo.getUserType());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return loggedInUserInfo;
	}

	
	// Close the account
	public boolean deleteTheUser(String userName) {
		// TODO Auto-generated method stub
		int count = 0;
		boolean result = false;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM tbl_users WHERE user_name =?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			count = pstmt.executeUpdate();
			if(count>0){
				result = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//Delete All Annoucements for the user
	public boolean deleteAllAnnouncementsForTheUser(String userName) {
		// TODO Auto-generated method stub
		int count = 0;
		boolean result = false;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM tbl_announcements WHERE announcer =?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			count = pstmt.executeUpdate();
			if(count>0){
				result = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//Update account information
	public UserInfoBean updateAccountInfo(UserInfoBean updateAccountInfo){
		int count = 0;
		UserInfoBean loginInfo = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE tbl_users SET password = ?, first_name = ?, last_name = ?, email_address = ? WHERE user_name = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, updateAccountInfo.getPassword());
			pstmt.setString(2, updateAccountInfo.getFirstName());
			pstmt.setString(3, updateAccountInfo.getLastName());
			pstmt.setString(4, updateAccountInfo.geteMailAddress());
			pstmt.setString(5, updateAccountInfo.getUserName());
			count = pstmt.executeUpdate();
			if(count>0){
				//return loginInfo = userName, firstName, userType
				loginInfo = selectLoginInfo(updateAccountInfo.getUserName());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return loginInfo;
	}


	
	//get Account Information for the given user
	public UserInfoBean selectAccountInfo(String userName) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserInfoBean accountInfo = null;
		String sql = "SELECT user_name, password, first_name, last_name, email_address FROM tbl_users WHERE user_name= ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				accountInfo = new UserInfoBean();
				accountInfo.setUserName(rs.getString("user_name"));
				accountInfo.setPassword(rs.getString("password"));
				accountInfo.setFirstName(rs.getString("first_name"));
				accountInfo.setLastName(rs.getString("last_name"));
				accountInfo.seteMailAddress(rs.getString("email_address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return accountInfo;
	}

}
