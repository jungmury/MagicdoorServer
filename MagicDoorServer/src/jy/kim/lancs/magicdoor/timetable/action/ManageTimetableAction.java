package jy.kim.lancs.magicdoor.timetable.action;

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

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.rest.bean.EventObjectForCalendar;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;
import jy.kim.lancs.magicdoor.timetable.service.ManageTimetableService;
import jy.kim.lancs.magicdoor.util.TimeUtilForMagicdoor;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class ManageTimetableAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
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

		ManageTimetableService service = new ManageTimetableService();
		boolean isModification = service.checkIfExists(bean);
		boolean isSuccessful = false;
		if (isModification) {
			isSuccessful = service.modifyTimetable(bean);
			System.out.println("Modify timetable");
		} else {
			isSuccessful = service.addTimetable(bean);
			System.out.println("Insert timetable");
		}

		if (isSuccessful) {
			ArrayList<TimeTableBean> timetable = service
					.getTimetableOf(userName);

			ArrayList<EventObjectForCalendar> events = null;
			Calendar calendar = Calendar.getInstance();
			ObjectMapper mapper = new ObjectMapper();
			Writer writer = new StringWriter();
			String json = "";
			if (timetable != null) {
				events = new ArrayList<EventObjectForCalendar>();
				for (TimeTableBean t : timetable) {
					System.out.println(calendar.getTime());

					calendar.set(Calendar.DAY_OF_WEEK, t.day);
					calendar.set(Calendar.HOUR_OF_DAY, t.time.getHours());
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);

					// Make recurring events
					for (int i = 0; i < 2; i++) {

						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)
								+ (i * 7));
						Date d = new Date(calendar.getTimeInMillis());
						calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)
								- (i * 7));

						EventObjectForCalendar event = new EventObjectForCalendar();
						event.title = t.subject;
						event.id = String.valueOf(t.id);
						event.title = t.subject;
						event.allDay = false;
						event.url = "../ManageTimetable.action?";
						event.color = "purple";
						event.start = sdf.format(d);

						calendar.set(Calendar.HOUR_OF_DAY,
								calendar.get(Calendar.HOUR_OF_DAY) + 1);

						d.setHours(calendar.get(Calendar.HOUR_OF_DAY));
						event.end = sdf.format(d);
						events.add(event);

						calendar.set(Calendar.HOUR_OF_DAY,
								calendar.get(Calendar.HOUR_OF_DAY) - 1);

					}

				}
				try {
					mapper.writeValue(writer, events);
					json = writer.toString();
					System.out.println(json);
					File file = new File(request.getServletContext()
							.getRealPath("/timetable/timetable.json"));

					System.out.println("File path : "
							+ request.getServletContext().getRealPath(
									"/timetable/timetable.json"));
					if (!file.exists()) {
						file.createNewFile();
						System.out.println("File created");
					} else {
						file.delete();
						file.createNewFile();
						System.out.println("File re created");
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
				File file = new File(request.getServletContext().getRealPath(
						"/timetable/timetable.json"));

				System.out.println("File path : "
						+ request.getServletContext().getRealPath(
								"/timetable/timetable.json"));
				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("File created");
				} else {
					file.delete();
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("File re created");
				}
			}

			forward.setRedirect(true);
			forward.setUrl("timetable/timetable.jsp");

		}

		return forward;
	}

}
