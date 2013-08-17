package jy.kim.lancs.magicdoor.schedule.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.EventObjectForCalendar;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;
import jy.kim.lancs.magicdoor.schedule.service.ManageTimetableTeachingAndAppointmentService;
import jy.kim.lancs.magicdoor.util.TimeUtilForMagicdoor;
import jy.kim.lancs.magicdoor.vo.ActionForward;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ManageTimetableTeachingAndAppointmentAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("PostTimetableFromScheduleAction");
		String userName = request.getParameter("userName");
		String timeStr = request.getParameter("timeInfo");
		String subject = request.getParameter("subject");

		System.out.println("userName = " + userName);
		System.out.println("timeInfo = " + timeStr);
		System.out.println("subject = " + subject);

		ActionForward forward = new ActionForward();
		String[] timeInfo = timeStr.split(" ");
		System.out.println("Day: " + timeInfo[0] + " Month: " + timeInfo[1]
				+ " Date: " + timeInfo[2] + " Year: " + timeInfo[3] + " Time: "
				+ timeInfo[4]);
		int day = TimeUtilForMagicdoor.getDayValue(timeInfo[0]);
		Time hour = TimeUtilForMagicdoor.getHourValue(timeInfo[4]);
		TimeTableBean bean = new TimeTableBean();
		bean.day = day;
		bean.userName = userName;
		bean.subject = subject;
		bean.time = hour;

		ManageTimetableTeachingAndAppointmentService service = new ManageTimetableTeachingAndAppointmentService();
		boolean isModification = service.checkIfExists(bean);
		boolean isSuccessful = false;
		if (isModification) {
			isSuccessful = service.modifyTimetable(bean);
			System.out.println("Modify timetable");
		} else {
			isSuccessful = service.addTimetable(bean);
			System.out.println("Insert timetable");
		}
		
		ArrayList<AppointmentRespDetailBean> appointments = service
				.getAppointmentsOf(userName);
		if (isSuccessful) {

			ArrayList<TimeTableBean> timeTable = service
					.getTimetableOf(userName);

			ArrayList<EventObjectForCalendar> events = null;
			ObjectMapper mapper = new ObjectMapper();
			Writer writer = new StringWriter();
			String json = "";

			Calendar calendarForApp = Calendar.getInstance();
			Calendar calendarForTime = Calendar.getInstance();

			File file = new File(request.getServletContext().getRealPath(
					"/schedule/schedule.json"));
			if (file.exists()) {
				file.delete();
				System.out.println("File deleted");
			}
			if (appointments != null) {
				System.out.println("If");
				events = new ArrayList<EventObjectForCalendar>();

				for (AppointmentRespDetailBean appointment : appointments) {
					System.out.println(calendarForApp.getTime());
					EventObjectForCalendar event = new EventObjectForCalendar();
					event.title = appointment.title;
					calendarForApp.set(Calendar.MONTH,
							appointment.bookingDate.getMonth());
					calendarForApp.set(Calendar.DATE,
							appointment.bookingDate.getDate());
					calendarForApp.set(Calendar.HOUR_OF_DAY,
							appointment.bookingTime.getHours());
					calendarForApp.set(Calendar.MINUTE, 0);
					calendarForApp.set(Calendar.SECOND, 0);

					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Date d = new Date(calendarForApp.getTimeInMillis());
					event.id = appointment.refValue;
					event.start = sdf.format(d);
					calendarForApp.set(Calendar.HOUR_OF_DAY,
							calendarForApp.get(Calendar.HOUR_OF_DAY) + 1);
					d.setHours(calendarForApp.get(Calendar.HOUR_OF_DAY));
					event.end = sdf.format(d);
					event.allDay = false;
					event.url = "../GetAppointmentContentCalendarView.action?refValue=";
					
					// event.url = "../GetSelectedSchedule.action?id=";
					events.add(event);

				}
				if (timeTable != null) {
					for (TimeTableBean t : timeTable) {
						calendarForTime.set(Calendar.DAY_OF_WEEK, t.day);
						calendarForTime
								.set(Calendar.HOUR_OF_DAY, t.time.getHours());
						calendarForTime.set(Calendar.MINUTE, 0);
						calendarForTime.set(Calendar.SECOND, 0);

						// Make recurring events
						for (int i = -4; i < 5; i++) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
		//					System.out.println("************");
		//					System.out.println("Before date: "
		//							+ calendarForTime.get(Calendar.DATE));
							calendarForTime.set(Calendar.DATE,
									calendarForTime.get(Calendar.DATE) + (i * 7));
		//					System.out.println("After date: "
		//							+ calendarForTime.get(Calendar.DATE));

		//					System.out.println("cal " + calendarForTime.getTime());

							Date d = new Date(calendarForTime.getTimeInMillis());
		//					System.out.println("d " + d.toString());
		//					System.out.println("************");

							calendarForTime.set(Calendar.DATE,
									calendarForTime.get(Calendar.DATE) - (i * 7));

							EventObjectForCalendar event = new EventObjectForCalendar();
							event.title = t.subject;
							event.id = String.valueOf(t.id);
							event.title = t.subject;
							event.allDay = false;
							event.color = "purple";
							// event.url = "../ManageTimetable.action?";

							event.start = sdf.format(d);

							calendarForTime.set(Calendar.HOUR_OF_DAY,
									calendarForTime.get(Calendar.HOUR_OF_DAY) + 1);

							d.setHours(calendarForTime.get(Calendar.HOUR_OF_DAY));
							event.end = sdf.format(d);
							events.add(event);

							calendarForTime.set(Calendar.HOUR_OF_DAY,
									calendarForTime.get(Calendar.HOUR_OF_DAY) - 1);

						}

					}
				}
				try {
					mapper.writeValue(writer, events);
					json = writer.toString();
					System.out.println(json);
					file = new File(request.getServletContext().getRealPath(
							"/schedule/schedule.json"));

					System.out.println("File path : "
							+ request.getServletContext().getRealPath(
									"/schedule/schedule.json"));
					if (!file.exists()) {
						file.createNewFile();
						System.out.println("File created");
					} else {
						System.out.println("File not created");
					}
					System.out.println("Write to file begins");
					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(json);
					bw.close();
					System.out.println("Write to file ends");

				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Else");
				events = new ArrayList<EventObjectForCalendar>();
				if (timeTable != null) {
					for (TimeTableBean t : timeTable) {
						calendarForTime.set(Calendar.DAY_OF_WEEK, t.day);
						calendarForTime
								.set(Calendar.HOUR_OF_DAY, t.time.getHours());
						calendarForTime.set(Calendar.MINUTE, 0);
						calendarForTime.set(Calendar.SECOND, 0);

						// Make recurring events
						for (int i = -4; i < 5; i++) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							System.out.println("************");
							System.out.println("Before date: "
									+ calendarForTime.get(Calendar.DATE));
							calendarForTime.set(Calendar.DATE,
									calendarForTime.get(Calendar.DATE) + (i * 7));
							System.out.println("After date: "
									+ calendarForTime.get(Calendar.DATE));

							System.out.println("cal " + calendarForTime.getTime());

							Date d = new Date(calendarForTime.getTimeInMillis());
							System.out.println("d " + d.toString());
							System.out.println("************");

							calendarForTime.set(Calendar.DATE,
									calendarForTime.get(Calendar.DATE) - (i * 7));

							EventObjectForCalendar event = new EventObjectForCalendar();
							event.title = t.subject;
							event.id = String.valueOf(t.id);
							event.title = t.subject;
							event.allDay = false;
							event.color = "purple";
							// event.url = "../ManageTimetable.action?";

							event.start = sdf.format(d);

							calendarForTime.set(Calendar.HOUR_OF_DAY,
									calendarForTime.get(Calendar.HOUR_OF_DAY) + 1);

							d.setHours(calendarForTime.get(Calendar.HOUR_OF_DAY));
							event.end = sdf.format(d);
							events.add(event);

							calendarForTime.set(Calendar.HOUR_OF_DAY,
									calendarForTime.get(Calendar.HOUR_OF_DAY) - 1);
						}
						try {
							mapper.writeValue(writer, events);
							json = writer.toString();
							System.out.println(json);
							file = new File(request.getServletContext()
									.getRealPath("/schedule/schedule.json"));

							System.out.println("File path : "
									+ request.getServletContext().getRealPath(
											"/schedule/schedule.json"));
							if (!file.exists()) {
								file.createNewFile();
								System.out.println("File created");
							} else {
								System.out.println("File not created");
							}
							System.out.println("Write to file begins");
							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(json);
							bw.close();
							System.out.println("Write to file ends");

						} catch (JsonGenerationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			forward.setRedirect(true);
			forward.setUrl("schedule/teaching_and_appointment_view.jsp");

		}

		return forward;
	}

}
