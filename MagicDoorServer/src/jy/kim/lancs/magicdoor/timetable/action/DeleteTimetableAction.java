package jy.kim.lancs.magicdoor.timetable.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.rest.bean.EventObjectForCalendar;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;
import jy.kim.lancs.magicdoor.timetable.service.ManageTimetableService;
import jy.kim.lancs.magicdoor.vo.ActionForward;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class DeleteTimetableAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String userName = request.getParameter("userName");

		System.out.println("id = " + id);
		System.out.println("userName" + userName);

		ActionForward forward = new ActionForward();

		ManageTimetableService service = new ManageTimetableService();

		boolean isSuccessful = service.deleteTimetable(id);
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
					calendar.set(Calendar.DAY_OF_WEEK, t.day);
					calendar.set(Calendar.HOUR_OF_DAY, t.time.getHours());
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);

					// Make recurring events
					for (int i = -4; i < 5; i++) {

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
