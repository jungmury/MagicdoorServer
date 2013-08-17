<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="jy.kim.lancs.magicdoor.rest.bean.EventObjectForCalendar"%>
<%@page import="jy.kim.lancs.magicdoor.bean.UserInfoBean"%>
<html>
<head>
<%
	UserInfoBean userInfo = (UserInfoBean) session
			.getAttribute("userInfo");
%>
<link href='../fullcalendar/fullcalendar.css' rel='stylesheet' />
<link href='../fullcalendar/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<script src='../jquery/jquery-1.9.1.min.js'></script>
<script src='../jquery/jquery-ui-1.10.2.custom.min.js'></script>
<script src='../fullcalendar/fullcalendar.min.js'></script>

<script type='text/javascript'>
	$(document)
			.ready(
					function() {
						var calendar = $('#calendar')
								.fullCalendar(
										{
											contentHeight : 700,
											weekends : false,
											allDaySlot : false,
											minTime : 9,
											maxTime : 18,
											defaultView : 'agendaWeek',
											/*
											titleFormat : {
												month : '',
												week : "",
												day : ''
											},
											columnFormat : {
												month : '',
												week : 'ddd',
												day : 'dddd'
											},*/
											header : {
												left : '',
												center : 'title',
												right : ''
											},

											slotMinutes : 60,
											selectable : true,
											selectHelper : true,
											select : function(start, end,
													allDay) {

												var title = prompt('Enter New Subject:');
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
													calendar
															.fullCalendar('unselect');
													window.location.href = "../ManageTimetable.action?userName="
															+ document
																	.getElementById("userName").value
															+ "&subject="
															+ title
															+ "&timeInfo="
															+ start;

												} else {
													return false;
												}
											},
											eventSources : [ 'timetable.json' ],
											eventClick : function(event) {
												var cond = confirm("Delete? \nPress Cancel button to modify the subject title");
												if (cond == true) {
													$('#calendar')
															.fullCalendar(
																	'removeEvents',
																	event.id);
													window.location.href = "../DeleteTimetable.action?userName="
															+ document
																	.getElementById("userName").value
															+ "&id=" + event.id;
													return false;
												} else {
													var subject = prompt(
															'Modify Subject:',
															event.title);

													if (subject != null) {
														if (event.url) {
															window.location.href = event.url
																	+ "userName="
																	+ document
																			.getElementById("userName").value
																	+ "&subject="
																	+ subject
																	+ "&timeInfo="
																	+ event.start;
															return false;
														}
													} else {
														return false;
													}
												}
											}
										});

					});
</script>
<style type='text/css'>
body {
	margin-top: 40px;
	text-align: center;
	font-size: 14px;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
}

#calendar {
	width: 900px;
	margin: 0 auto;
}
</style>
</head>
<body>

	<input type="hidden" id="userName" name="userName"
		value=<%=userInfo.getUserName()%>>
	<div>
		<h2 align="center">Timetable</h2>
	</div>
	<div align="right">
		Teaching Timetable :&nbsp;<img alt="" src="../img/teaching.png">
	</div>

	<div id='calendar'></div>
</body>
</html>

