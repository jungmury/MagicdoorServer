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
		), color-stop(1, #dfdfdf));
	background: -moz-linear-gradient(center top, #ededed 5%, #dfdfdf 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ededed',
		endColorstr='#dfdfdf');
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
}

.button:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #dfdfdf
		), color-stop(1, #ededed));
	background: -moz-linear-gradient(center top, #dfdfdf 5%, #ededed 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#dfdfdf',
		endColorstr='#ededed');
	background-color: #dfdfdf;
}

.button:active {
	position: relative;
	top: 1px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="login/logout.jsp">
		<%
			UserInfoBean loggedInUserInfo = (UserInfoBean) session
					.getAttribute("userInfo");
			if (loggedInUserInfo != null) {
				session = request.getSession();
		%>
		Hello,&nbsp;
		<%=loggedInUserInfo.getFirstName()%>!
		<script type="text/javascript">
			setTimeout(function() {
				parent.mainFrame.location.href = "initial_main_frame.jsp";
				parent.menuFrame.location.reload(true); //update session info
			}, 0); //will call the function after 2 secs.
		</script>
		&nbsp; <input type="submit" class="button" value="Log Out">
	</form>
	<%
		} else {
	%>
	<div align="right">
		<a href="login/login.jsp" class="button" type="button"
			target="mainFrame">Log In</a> &nbsp;&nbsp; <a href="login/signup.jsp"
			class="button" type="button" target="mainFrame">Sign Up</a>
	</div>
	<%
		}
	%>
</body>
</html>