<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="jy.kim.lancs.magicdoor.rest.bean.EventObjectForCalendar"%>
<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<html>
<head>
<link href='../fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='../fullcalendar/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<script src='../jquery/jquery-1.9.1.min.js'></script>
<script src='../jquery/jquery-ui-1.10.2.custom.min.js'></script>
<script src='../fullcalendar/fullcalendar.min.js'></script>
<script>
	$(document).ready(function() {
		$('#calendar').fullCalendar({
			allDaySlot : false,
			minTime : 9,
			maxTime : 18,
			weekends : false,
			defaultView : 'agendaWeek',
			/*	dayClick : function() {
					alert('a day has been clicked!');
				},*/

			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek'
			},
			eventSources : [ 'appointments.json' ],

			eventClick : function(event) {
				if (event.url) {
					window.location.href = event.url + event.id;
					return false;
				}
			},
		});
	});
</script>
<style>
body {
	margin-top: 40px;
	text-align: center;
	font-size: 14px;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
}

#loading {
	position: absolute;
	top: 5px;
	right: 5px;
}

#calendar {
	width: 900px;
	margin: 0 auto;
}
</style>
</head>
<%
	UserInfoBean userInfo = (UserInfoBean) session
			.getAttribute("userInfo");
%>
<body>
	<div>
		<h2 align="center">Appointment</h2>
	</div>
	<div align="right">
		Appointment to be confirmed or not :&nbsp;<img alt=""
			src="../img/appointment_to_be_confirmed.png"><br>Confirmed
		Appointment :&nbsp;<img alt="" src="../img/appointment_confirmed.png">
	</div>
	<br>

	<div id='loading' style='display: none'>loading...</div>

	<div id='calendar'></div>
</body>
</html>
