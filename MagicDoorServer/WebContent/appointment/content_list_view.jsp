<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<%@page
	import="jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<html>
<head>
<style type="text/css">
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
		if (confirm('Are you sure you do not want to confirm the request?')) {
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
		<h4>Appointment Detail</h4>
	</center>
	<center>
		<%
			UserInfoBean userInfo = (UserInfoBean) session
					.getAttribute("userInfo");
			AppointmentRespDetailBean appointment = (AppointmentRespDetailBean) request
					.getAttribute("appointment");
		%>
		<br>
		<%
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		%>

		<table width="80%" border="1">


			<tr>
				<td align="center" width="400">Appointment Date</td>
				<td align="center" width="400"><%=sdf.format(appointment.bookingDate)%></td>
				<td align="center" width="400">Time</td>
				<td align="center" width="400"><%=appointment.bookingTime%></td>
			</tr>
			<tr>
				<td align="center" width="400">Status</td>
				<%
					if (appointment.status.equals("requested")) {
						String status = "To be confirmed";
				%>
				<td align="center" width="400" style="color: orange;"><%=status%></td>
				<%
					} else if (appointment.status.equals("confirmed")) {
				%>
				<td align="center" width="400" style="color: blue;"><%=appointment.status%></td>
				<%
					} else if (appointment.status.equals("rejected")) {
				%>
				<td align="center" width="400" style="color: red;"><%=appointment.status%></td>

				<%
					} else {
				%>
				<td align="center" width="400"><%=appointment.status%></td>

				<%
					}
				%>
				<td align="center" width="400">Requester</td>
				<td align="center" width="400"><%=appointment.name%></td>
			</tr>
			<tr>
				<td align="center" width="400">Title</td>
				<td align="center" width="400" colspan="3"
					onkeyup="fc_chk_byte(this,10)"><%=appointment.title%></td>
			</tr>

			<tr>
				<td align="center" width="400">Description</td>
				<td align="left" width="4000" colspan="3"><pre><%=appointment.description%></pre></td>
			</tr>

			<tr>
				<!--  -->
				<td colspan="4">
				
				<a class="button" href="initial_main_frame.jsp"
					type="button" target="mainFrame">Main Page</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<%if(!appointment.status.equals("confirmed")) {%>
					<a class="button"
					href="ConfirmAppointmentRequestListView.action?refValue=<%=appointment.refValue%>"
					type="button" onclick="return confirmConfirm();">Confirm
						Request</a>&nbsp;&nbsp;&nbsp;&nbsp; 
					<%
						} 
					%>
					<a class="button"
					href="RejectAppointmentRequestListView.action?refValue=<%=appointment.refValue%>"
					type="button" onclick="">Reject Request</a>
					&nbsp;&nbsp;&nbsp;&nbsp; 
					
					<a class="button"
					href="GetAppointmentListView.action?userName=<%=userInfo.getUserName()%>"
					type="button">Back to List</a> &nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
		</table>
	</center>
</body>
</html>