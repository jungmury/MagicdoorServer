<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Calendar"%>
<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
textarea {
	width: 96%;
	height: 90px;
	border-radius: 3px;
	border: 1px solid #CCC;
	padding: 8px;
	font-weight: 200;
	font-size: 15px;
	font-family: Verdana;
	box-shadow: 1px 1px 5px #CCC;
}

textarea:hover {
	width: 96%;
	height: 90px;
	border-radius: 3px;
	border: 1px solid #aaa;
	padding: 8px;
	font-weight: 200;
	font-size: 15px;
	font-family: Verdana;
	box-shadow: 1px 1px 5px #CCC;
}

input {
	width: 96%;
	height: 10px;
	border-radius: 3px;
	border: 1px solid #CCC;
	padding: 8px;
	font-weight: 200;
	font-size: 15px;
	font-family: Verdana;
	box-shadow: 1px 1px 5px #CCC;
}

input:hover {
	width: 96%;
	height: 10px;
	border-radius: 3px;
	border: 1px solid #aaa;
	padding: 8px;
	font-weight: 200;
	font-size: 15px;
	font-family: Verdana;
	box-shadow: 1px 1px 5px #CCC;
}
/*input type for submit button*/
.button {
	width: 250px;
	height: 40px;
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
}

.button:hover {
	width: 250px;
	height: 40px;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #dfdfdf
		), color-stop(1, #ededed));
	background: -moz-linear-gradient(center top, #dfdfdf 5%, #ededed 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#dfdfdf',
		endColorstr='#ededed');
	background-color: #dfdfdf;
}

.button:active {
	width: 250px;
	height: 40px;
	position: relative;
	top: 1px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		session = request.getSession();
		UserInfoBean loggedInUserInfo = (UserInfoBean) session
				.getAttribute("userInfo");
		Calendar calendar = Calendar.getInstance(Locale.UK);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
	%>
	<center>
		<h3>Post Announcement</h3>
		Today : &nbsp;
		<%=sdf.format(calendar.getTime())%>

		<form action="../PostAnnouncement.action" target="mainFrame">
			<input type="hidden" name="userName"
				value="<%=loggedInUserInfo.getUserName()%>">
			<table border="1" width="80%" align="center">
				<tr>
					<td align="center" >Announcement Title</td>
					<td align="center"><input class="textarea" type="text"
						name="announcementTitle" id="announcementTitle"></td>
				</tr>
				<tr>
					<td align="center">Content</td>
					<td align="center"><textarea class="textarea"
							name="announcementContent"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="button" type="submit"
						value="Post Announcement" align="middle"></td>
				</tr>
			</table>

		</form>
	</center>
</body>
</html>