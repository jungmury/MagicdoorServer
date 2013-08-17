<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
.button {
	width: 100px;
	height: 35px;
	margin: 5px;
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
	-moz-box-shadow: inset 0px 1px 0px 0px #ffffff;
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>

</head>

<script language="javascript"
	src="<%=request.getContextPath()%>/js/script.js"></script>
<body background="image/background.png">
	<%
		UserInfoBean userInfo = (UserInfoBean) session
				.getAttribute("userInfo");
		boolean isLoggedIn = false;
		if (userInfo != null) {
			isLoggedIn = true;
		}
	%>
	<!-- 
		<a class="button" href="appointment/calendar_view.jsp"
		target="mainFrame" onclick="return checkLogin(<%=isLoggedIn%>);">Appointment
		Calendar</a>
	
	 -->

	<%
		if (isLoggedIn) {
	%>

	<a class="button"
		href="GetTimetable.action?userName=<%=userInfo.getUserName()%>"
		target="mainFrame" onclick="return checkLogin(<%=isLoggedIn%>);">Teaching
		Timetable</a>


	<a class="button"
		href="GetAppointmentCalendarView.action?userName=<%=userInfo.getUserName()%>"
		target="mainFrame" onclick="return checkLogin(<%=isLoggedIn%>);">Appointment
		Calendar</a>

	<a class="button"
		href="GetTeachingAndAppointment.action?userName=<%=userInfo.getUserName()%>"
		target="mainFrame" onclick="return checkLogin(<%=isLoggedIn%>);">Teaching
		& Appointment</a>

<!-- 
	<a class="button"
		href="GetAppointmentListView.action?userName=<%=userInfo.getUserName()%>"
		target="mainFrame" onclick="return checkLogin(<%=isLoggedIn%>);">Appointment
		List</a>
-->
	

	<a class="button" href="announcement/post_announcement_form.jsp"
		target="mainFrame" onclick="return checkLogin(<%=isLoggedIn%>);">Post
		Announcement</a>

	<a class="button"
		href="GetAnnouncementList.action?userName=<%=userInfo.getUserName()%>"
		target="mainFrame" onclick="return checkLogin(<%=isLoggedIn%>);">Manage
		Announcement</a>


	<a class="button"
		href="GetReceivedMessageList.action?userName=<%=userInfo.getUserName()%>"
		target="mainFrame" onclick="return checkLogin(<%=isLoggedIn%>);">Received
		Messages</a>

	<a class="button"
		href="GetSentMessageList.action?userName=<%=userInfo.getUserName()%>"
		target="mainFrame" onclick="return checkLogin(<%=isLoggedIn%>);">Sent
		Messages</a>

	<%
		} else {
	%>
	<a class="button" href="" target="mainFrame"
		onclick="return checkLogin(<%=isLoggedIn%>);">Teaching Timetable</a>

	<a class="button" href="" target="mainFrame"
		onclick="return checkLogin(<%=isLoggedIn%>);">Appointment Calendar</a>

	<a class="button" href="" target="mainFrame"
		onclick="return checkLogin(<%=isLoggedIn%>);">Teaching &
		Appointment</a>

<!-- 
	<a class="button" href="" target="mainFrame"
		onclick="return checkLogin(<%=isLoggedIn%>);">Appointment List</a>
 -->
	<a class="button" href="" target="mainFrame"
		onclick="return checkLogin(<%=isLoggedIn%>);">Post Announcement</a>

	<a class="button" href="" target="mainFrame"
		onclick="return checkLogin(<%=isLoggedIn%>);">Manage Announcement</a>


	<a class="button" href="" target="mainFrame"
		onclick="return checkLogin(<%=isLoggedIn%>);">Received Messages</a>

	<a class="button" href="" target="mainFrame"
		onclick="return checkLogin(<%=isLoggedIn%>);">Sent Messages</a>


	<%
		}
	%>

</body>
</html>