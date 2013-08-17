<%@page
	import="jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<%!SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //날짜 출력 형태 정의%>

	<%
		ArrayList<AppointmentRespDetailBean> appointments = (ArrayList<AppointmentRespDetailBean>) request
				.getAttribute("appointmentList");
	%>
	<center>
		<h3>Appointments</h3>
	</center>
	<%
		if (appointments == null) { //if there is no annoucements
	%>

	<table width="100%">
		<tr>
			<td align="center">There are no appointments</td>
		</tr>
	</table>
	<%
		} else {

			if (!(appointments.size() > 0)) {
	%>
	<table width="100%">
		<tr>
			<td align="center">There are no appointments</td>
		</tr>
	</table>
	<%
		} else {
	%>

	<table border="1" width="80%" align="center">
		<tr>
			<td align="center" width="15%"><b>Appointment Date</b></td>
			<td align="center" width="15%"><b>Appointment Time</b></td>
			<td align="center" width="40%"><b>Title</b></td>
			<td align="center" width="15%"><b>Requester</b></td>
			<td align="center" width="15%"><b>Status</b></td>
		</tr>
		<%
			AppointmentRespDetailBean appointment = null;
					for (int i = 0; i < appointments.size(); i++) {
						appointment = appointments.get(i);
		%>
		<tr>
			<!-- 여기까지가 제목 -->
			<td align="center"><%=sdf.format(appointment.bookingDate)%></td>
			<td align="center"><%=appointment.bookingTime%></td>
			<td align="center"><a
				href="GetAppointmentContentListView.action?refValue=<%=appointment.refValue%>"><%=appointment.title%></a></td>
			<td align="center"><%=appointment.name%></td>
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

		</tr>
		<%
			}
		%>
	</table>

	<%
		}
		}
	%>

</body>
</html>

