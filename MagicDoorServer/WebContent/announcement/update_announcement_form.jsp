<%@page import="jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean"%>
<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.button {
	margin: 5px; -moz-box-shadow : inset 0px 1px 0px 0px #ffffff;
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
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>


<body>
	<%
		UserInfoBean userInfo = (UserInfoBean) session
				.getAttribute("userInfo");
		if (userInfo == null) {
	%>
	<script language="javascript">
		alert("Pleae log in.");
	</script>
	<%
		} else {
			AnnouncementRespBean announcemnt = (AnnouncementRespBean) request
					.getAttribute("currentAnnouncement");

			if (userInfo.getUserName().equals(announcemnt.getUserName())) {
	%>
	<form method="post" action="UpdateAnnouncementPro.action">

		<!-- <form method="post" name="writeForm" action="boardUpdatePro.bo" onSubmit="return writeSave()"> -->
		<input type="hidden" name="annNum"
			value="<%=announcemnt.getAnnouncementNum()%>">

		<!-- hidden : 화면에는 보이지 않지만 서버쪽으로 특정한 파라미터를 전송할 때 사용한다.
	 		  form안에 있는 inputbox 데이터와 함께 넘어간다. -->

		<!-- 작성한 글이 답변글일 경우만 hidden 타입으로 설정한 값들이 전송됨 -->

		<table width="80%" border="1" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td align="center" colspan="2">Update the Announcement</td>
			</tr>

			<tr>
				<td width="70" align="center">Announcer</td>
				<td width="330"><input type="text" disabled="disabled"
					size="10" name="userName" value="<%=announcemnt.getUserName()%>"></td>
			</tr>

			<tr>
				<td width="70" align="center">Title</td>
				<td width="330"><input type="text" size="40"
					name="announcementTitle" value="<%=announcemnt.getTitle()%>"></td>
			</tr>


			<tr>
				<td width="70" align="center">Content</td>
				<td width="330"><textarea name="announcementContent" rows="13"
						cols="40"><%=announcemnt.getAnnouncementContent()%></textarea></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><a class="button"
					href="GetAnnouncementList.action?userName=<%=userInfo.getUserName()%>"
					type="button">Back to List</a>&nbsp;&nbsp;&nbsp;&nbsp; <a
					class="button" href="initial_main_frame.jsp" type="button"
					target="mainFrame">Main</a>&nbsp;&nbsp;&nbsp;&nbsp; <input
					class="button" type="submit" value="Update">&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</form>
	<%
		}
		}
	%>
</body>
</html>