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
			weekends : false,
			allDaySlot : false,
			minTime : 9,
			maxTime : 18,
			defaultView : 'agendaWeek',
			titleFormat : {
				month : '',
				week : "",
				day : ''
			},
			columnFormat : {
				month : '',
				week : 'ddd',
				day : 'dddd'
			},
			header : {
				left : '',
				center : 'title',
				right : ''
			},
			editable : true,
			eventSources : [ 'timetable.json' ],
			eventClick : function(event) {
				if (event.url) {
					window.location.href(event.url + event.id);
					return false;
				}
			},
			slotMinutes : 60,
			selectable : true,
			selectHelper : true,
			select: function(start, end, allDay) {
				var title = prompt('Subject Title:');
				if (title) {
					calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
				calendar.fullCalendar('unselect');
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
	<div id='loading' style='display: none'>loading...</div>
	<div align="center">
		<h3>Lecture Timetable</h3>
	</div>
	<div id='calendar'></div>
</body>
</html>
