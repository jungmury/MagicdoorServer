<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.button {
	-moz-box-shadow: inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow: inset 0px 1px 0px 0px #ffffff;
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ededed
		), color-stop(1, #dfdfdf) );
	background: -moz-linear-gradient(center top, #ededed 5%, #dfdfdf 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ededed',
		endColorstr='#dfdfdf' );
	background-color: #ededed;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	border: 1px solid #dcdcdc;
	display: inline-block;
	color: #777777;
	font-family: arial;
	font-size: 15px;
	font-weight: bold;
	padding: 6px 24px;
	text-decoration: none;
	text-shadow: 1px 1px 0px #ffffff;
}.button:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #dfdfdf
		), color-stop(1, #ededed) );
	background: -moz-linear-gradient(center top, #dfdfdf 5%, #ededed 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#dfdfdf',
		endColorstr='#ededed' );
	background-color: #dfdfdf;
}.button:active {
	position: relative;
	top: 1px;
}
/* This imageless css button was generated by CSSButtonGenerator.com */
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Page</title>
<script language="javascript" src="<%=request.getContextPath()%>/js/script.js"></script>
</head>
<body>
<%
	session = request.getSession();
	UserInfoBean loggedInUserInfo = (UserInfoBean)session.getAttribute("userInfo");
	
	if(loggedInUserInfo != null) {
%>
	<%=loggedInUserInfo.getUserName() %>'s information page<br><br>
	
	<form action="RetrieveAccountInfoAction.login" method="post">
	<input type="hidden" name="userName" value=<%=loggedInUserInfo.getUserName()%>>
	<input type="submit" class="button" name="updateAccountInfo" value="Update Account Info">
	</form>
	<br>
	
	<form action="CloseAccountAction.login" method="post" onsubmit="return doubleCheckToCloseAccount()">
	<input type="hidden" name="userName" value=<%=loggedInUserInfo.getUserName()%>>
	<input type="submit" class="button" name="closeAccount" value="Close Account">
	</form>
<%
	}	
%>
</body>
</html>