<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		UserInfoBean loggedInUserInfo = (UserInfoBean) session
				.getAttribute("userInfo");

		if (loggedInUserInfo != null) {
	%>
	User:
	<%=loggedInUserInfo.getUserName()%>Logged Out
	<%
		session.invalidate();
		}
	%>

	<script type="text/javascript">
		setTimeout(function() {
			window.location.href = "../top_menu.jsp"; //will redirect to your blog page (an ex: blog.html)
			parent.menuFrame.location.reload(true); //update session info
			parent.mainFrame.location.href = "../initial_main_frame.jsp";
		}, 0); //will call the function after 2 secs.
	</script>

</body>
</html>