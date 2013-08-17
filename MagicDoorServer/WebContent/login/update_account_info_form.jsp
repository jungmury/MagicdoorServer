<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Update Account Info</title>
<script language="javascript" src="<%=request.getContextPath()%>/js/script.js"></script>
</head>
<body>
<%
	session = request.getSession();
	UserInfoBean loggedInUserInfo = (UserInfoBean)session.getAttribute("userInfo");
	
	if(loggedInUserInfo != null) {
		String userName = loggedInUserInfo.getUserName();
		String password = loggedInUserInfo.getPassword();
		String firstName = loggedInUserInfo.getFirstName();
		String lastName = loggedInUserInfo.getLastName();
		String eMailAddress = loggedInUserInfo.geteMailAddress();
%>
	<h3>Update Account Info Page</h3>
	<form name="updateAccountInfoForm" id="updateAccountInfoForm" action="UpdateAccountInfoAction.login" method="post" onsubmit="return checkUpdateAccountFormBlank('divCheckPasswordMatch')">
		<input type="hidden" name="userName" id="userName" value=<%=userName %>>
		<div id="loginBox">
			<div id="loginHeader">Login</div>
			<div id="formVal">
				<div>User Name :
					<%=userName%>
				</div>
				<div>Password:
					<input type="password" name="password" id="password" value= <%=password %> onkeyup="return checkPasswordMatch('updateAccountInfoForm', 'divCheckPasswordMatch')"><span
						style="font-size: 10px;"></span>
				</div>
				
				<div>Confirm Password:
					<input type="password" name="confirmPassword" id="confirmPassword"  value= <%=password %> onkeyup="return checkPasswordMatch('updateAccountInfoForm', 'divCheckPasswordMatch')"><span
						style="font-size: 10px;"></span>
				</div>
				
  				<div class="updateAccountInfoFormAlert" id="divCheckPasswordMatch" style="color:red;">
				</div>
  			
				<div>First Name:
					<input type="text" name="firstName" id="firstName" value=<%= firstName %>><span
						style="font-size: 10px;"></span>
				</div>
				
				<div>Last Name:
					<input type="text" name="lastName" id="lastName" value=<%= lastName%>><span
						style="font-size: 10px;"></span>
				</div>
				
				<div>E-Mail Address:
					<input type="text" name="eMailAddress" id="eMailAddress" value=<%= loggedInUserInfo.geteMailAddress()%>><span
						style="font-size: 10px;"></span>
				</div>
				<div style="clear: both; height: 0px;"></div>
	
				<div id="msgbox"></div>
			</div>
			<div id="loginFooter">
				<label> <input type="submit" name="ignUp" id="signUp"
					value="Update" >
				</label>
			</div>
		</div>
	</form>
<% } else { %>
	problem with session
<% 
	}
%>
</body>
</html>