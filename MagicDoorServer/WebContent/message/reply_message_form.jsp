<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="jy.kim.lancs.magicdoor.rest.bean.MessageBean"%>
<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<%@page
	import="jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<html>
<head>
<style type="text/css">
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

.button {
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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function confirmReject() {
		if (confirm('Are you sure you want to reject the request?')) {
			return true;
		} else {
			return false;
		}
	};
	function confirmConfirm() {
		if (confirm('Are you sure you want to confirm the request?')) {
			return true;
		} else {
			return false;
		}
	};
</script>
</head>
<body>


	<center>
		<h4>Message Detail</h4>
	</center>
	<center>
		<%
			UserInfoBean userInfo = (UserInfoBean) session
					.getAttribute("userInfo");
			MessageBean message = (MessageBean) request.getAttribute("message");
		%>
		<br>
		<%
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		%>

		<form action="ReplySelectedMessagePro.action">
			<table width="80%" border="1">
				<tr>
					<td align="center" width="400">Received Date</td>
					<td align="center" width="400"><%=sdf.format(message.sentDate)%></td>
					<td align="center" width="400">Replied</td>
					<%
						String status = "";
						if (message.status.equals("new")) {
							status = "No";
					%>
					<td align="center" width="400" style="color: red;"><%=status%></td>
					<%
						} else if (message.status.equals("replied")) {
							status = "Yes";
					%>
					<td align="center" width="400"><%=status%></td>
					<%
						}
					%>
				</tr>
				<tr>
					<td align="center" width="400">Received From</td>
					<td align="center" width="400" colspan="3"><%=message.name%></td>
				</tr>
				<tr>
				<tr>
					<td align="center" width="400">Message</td>
					<td align="left" width="4000" colspan="3"><pre><%=message.content%></pre></td>
				</tr>

				<tr>
					<td align="center" width="400">Type message to reply</td>
					<td align="center" width="4000" colspan="3"><textarea
							class="textarea" name="repliedMessage"></textarea></td>
				</tr>
				<tr>
					<!--  -->
					<td colspan="4"><a class="button"
						href="initial_main_frame.jsp" type="button" target="mainFrame">Main
							Page</a>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <input
						type="submit" class="button" value="Send">&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="button"
						href="GetReceivedMessageListAction?msgNo=<%=message.msgNo%>"
						type="button">Back to List</a> &nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>

			</table>
			<input type="hidden" name="msgNo" value=<%=message.msgNo%>>
		</form>
	</center>
</body>
</html>