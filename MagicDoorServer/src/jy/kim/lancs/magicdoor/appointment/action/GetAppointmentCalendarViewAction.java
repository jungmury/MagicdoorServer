package jy.kim.lancs.magicdoor.appointment.action;

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
import jy.kim.lancs.magicdoor.appointment.service.GetAppointmentService;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.EventObjectForCalendar;
import jy.kim.lancs.magicdoor.vo.ActionForward;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class GetAppointmentCalendarViewAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");

		System.out.println("userName = " + userName);

		GetAppointmentService service = new GetAppointmentService();
		ArrayList<AppointmentRespDetailBean> appointments = service
				.getAppointmentsOf(userName);
		ActionForward forward = new ActionForward();
		ArrayList<EventObjectForCalendar> events = null;
		if (appointments != null) {
			ObjectMapper mapper = new ObjectMapper();
			Writer writer = new StringWriter();
			String json = "";
			Calendar calendar = Calendar.getInstance();

			events = new ArrayList<EventObjectForCalendar>();

			for (AppointmentRespDetailBean appointment : appointments) {
				EventObjectForCalendar event = new EventObjectForCalendar();
				event.title = appointment.title;
				calendar.set(Calendar.MONTH, appointment.bookingDate.getMonth());
				calendar.set(Calendar.DATE, appointment.bookingDate.getDate());
				calendar.set(Calendar.HOUR_OF_DAY,
						appointment.bookingTime.getHours());
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date d = new Date(calendar.getTimeInMillis());
				event.id = appointment.refValue;
				event.start = sdf.format(d);
				calendar.set(Calendar.HOUR_OF_DAY,
						calendar.get(Calendar.HOUR_OF_DAY) + 1);
				d.setHours(calendar.get(Calendar.HOUR_OF_DAY));
				event.end = sdf.format(d);
				event.allDay = false;
				event.url = "../GetAppointmentContentCalendarView.action?refValue=";
				if(appointment.status.equals("confirmed")){
					event.color = "green";
				}
				events.add(event);
			}

			try {
				mapper.writeValue(writer, events);
				json = writer.toString();
				System.out.println(json);
				File file = new File(request.getServletContext().getRealPath(
						"/appointment/appointments.json"));

				System.out.println("File path : "
						+ request.getServletContext().getRealPath(
								"/appointment/appointments.json"));
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
			System.out.println("File deleted");
			
			File file = new File(request.getServletContext().getRealPath(
					"/appointment/appointments.json"));
			if (file.exists()) {
				file.delete();
			}

		}
		forward.setRedirect(true);
		forward.setUrl("appointment/calendar_view.jsp");

		return forward;
	}

}
