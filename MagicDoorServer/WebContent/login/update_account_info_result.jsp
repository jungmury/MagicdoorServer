<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META HTTP-EQUIV="refresh" CONTENT="1;URL=../index.jsp">

<title>Insert title here</title>
</head>
<body>
<%
	session = request.getSession();
	UserInfoBean loggedInUserInfo = (UserInfoBean)session.getAttribute("userInfo");
	if(loggedInUserInfo != null) {
		session = request.getSession();
%>
<h3>Login Successful</h3>

First Name = <%= loggedInUserInfo.getFirstName() %><br>
<%
	} else {
%>
	Your information was not updated properly!. Try again
<%} %>
</body>
</html>