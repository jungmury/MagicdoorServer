package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.QueryForAppointments;
import jy.kim.lancs.magicdoor.rest.service.RESTGETAppointmentsOfTheLecturerUserOnTheDateService;
import jy.kim.lancs.magicdoor.rest.util.JsonUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class RESTGETAppointmentsOfTheLecturerUserOnTheDateAction implements
		ActionForRESTWS {
	@Override
	public String excute(String... params) {
		// TODO Auto-generated method stub
		String result = null;
		ArrayList<AppointmentRespDetailBean> temp = null;
		RESTGETAppointmentsOfTheLecturerUserOnTheDateService service = new RESTGETAppointmentsOfTheLecturerUserOnTheDateService();
		ObjectMapper mapper = new ObjectMapper();

		System.out.println("get all apps of the date " + params[0]);
		QueryForAppointments query = null;
		if (params[0] != null) {
			query = JsonUtil.parsingQueryForAppointmentsWithDateOnly(params[0]);
		}
		// will return new announcements list
		temp = service.getAppointmentsOfTheLecturerUserOnTheDate(query);
		Writer writer = new StringWriter();
		try {
			// generate json using jacskon mapper
			mapper.writeValue(writer, temp);
			result = writer.toString();
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
		return result;
	
	}
}
