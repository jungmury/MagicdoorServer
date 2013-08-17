<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<frameset>
	<frameset rows="10%,*">
		<frame name="topFrame" src="top_menu.jsp" scrolling="no">

		<frameset cols="20%,*">
			<frame name="menuFrame" src="menu_list.jsp">
			<frame name="mainFrame" src="initial_main_frame.jsp">
		</frameset>

	</frameset>
</frameset>
</html>
