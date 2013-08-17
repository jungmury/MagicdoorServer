<%@page import="jy.kim.lancs.magicdoor.rest.bean.MessageBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function confirmDeleteAll() {
		if (confirm('Are you sure you want to delete the announcement?')) {
			return true;
		} else {
			return false;
		}
	};
</script>
</head>
<body background="image/background.png">
	<%!SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //날짜 출력 형태 정의%>

	<%
		ArrayList<MessageBean> messages = (ArrayList<MessageBean>) request
				.getAttribute("receivedMessageList");
	%>
	<center>
		<h3>Received Messages</h3>
	</center>

	<%
		if (messages == null) { //if there is no annoucements
	%>
	<table width="80%">
		<tr>
			<td align="center">There are no received messages</td>
		</tr>
	</table>
	<%
		} else {
	%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a class="button"
		href="DeleteAllAnnouncement.action?userName=<%=messages.get(0).receiver%>"
		type="button" onclick="return confirmDeleteAll();">Delete All</a>
	<br>

	<br>
	<table border="1" width="80%" align="center">
		<tr>
			<td align="center" width="150"><b>Received Date</b></td>
			<td align="center" width="300"><b>Received From</b></td>
			<td align="center" width="300"><b>Replied?</b></td>
		</tr>
		<%
			MessageBean message = null;
				for (int i = 0; i < messages.size(); i++) {
					message = messages.get(i);
		%>
		<tr>
			<!-- 여기까지가 제목 -->
			<td align="center"><%=sdf.format(message.sentDate)%></td>
			<td align="center"><a
				href="GetSelectedReceivedMessage.action?msgNo=<%=message.msgNo%>"><%=message.name%></a></td>
			<%
				if (message.status.equals("new")) {
			%>
			<td align="center" style="color: red;"><%=message.status%></td>
			<%
				} else {
			%>
			<td align="center"><%=message.status%></td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>

	<%
		}
	%>

</body>
</html>

