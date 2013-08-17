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
	$(document)
			.ready(
					function() {
						$('#calendar')
								.fullCalendar(
										{
											defaultView : 'agendaWeek',
											contentHeight : 700,
											weekends : false,
											allDaySlot : false,
											minTime : 9,
											maxTime : 18,
											slotMinutes : 60,
											header : {
												left : 'prev,next today',
												center : 'title',
												right : ''
											},
											selectHelper : true,
											selectable : true,
											select : function(start, end,
													allDay) {

												var title = prompt('Enter New Subject:');
												calendar
														.fullCalendar('unselect');
												if (title != null) {
													/*	calendar
													.fullCalendar(
															'renderEvent',
															{
																title : title,
																start : start,
																end : end,
																allDay : allDay,
															}, true // make the event "stick"
													);*/

													window.location.href = "../ManageTimetableTeachingAndAppointment.action?userName="
															+ document
																	.getElementById("userName").value
															+ "&subject="
															+ title
															+ "&timeInfo="
															+ start;
												} else {
													alert("??");
													return false;
												}
											},
											eventSources : [ 'schedule.json' ],
											eventClick : function(event) {
												if (event.url) {
													//
													window.location.href = event.url
															+ event.id;
													return false;
												} else {
													var cond = confirm("Delete? \nPress Cancel button to modify the subject title");
													if (cond == true) {
														$('#calendar')
																.fullCalendar(
																		'removeEvents',
																		event.id);
														window.location.href = "../DeleteTimetableTeachingAndAppointment.action?userName="
																+ document
																		.getElementById("userName").value
																+ "&id="
																+ event.id;
														return false;
													} else {
														var subject = prompt(
																'Modify Subject:',
																event.title);
														if (subject != null) {
															window.location.href = "../ManageTimetableTeachingAndAppointment.action?userName="
																	+ document
																			.getElementById("userName").value
																	+ "&subject="
																	+ subject
																	+ "&timeInfo="
																	+ event.start;
															return false;
														}

													}
												}
											}
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
	<input type="hidden" id="userName" name="userName"
		value=<%=userInfo.getUserName()%>>
	<div>
		<h2 align="center">Teaching & Appointment</h2>
	</div>
	<div align="right">
		Teaching Timetable :&nbsp;<img alt="" src="../img/teaching.png"><br>Appointment
		to be confirmed or not :&nbsp;<img alt=""
			src="../img/appointment_to_be_confirmed.png"><br>Confirmed
		Appointment :&nbsp;<img alt="" src="../img/appointment_confirmed.png">
	</div>

	<div id='loading' style='display: none'>loading...</div>
	<div id='calendar'></div>

</body>
</html>
