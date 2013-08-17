package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentReqDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.rest.service.RESTPutNewAppointmentService;
import jy.kim.lancs.magicdoor.rest.util.JsonUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class RESTPutNewAppointmentAction implements ActionForRESTWS{

	@Override
	public String excute(String... params) {
		// TODO Auto-generated method stub
		String result = null;
		ArrayList<AppointmentRespDetailBean> temp = null;
		
		RESTPutNewAppointmentService addNewAppSvc = new RESTPutNewAppointmentService();
		ObjectMapper mapper = new ObjectMapper();

		//appInfo
		System.out.println(params[0]);
		AppointmentReqDetailBean appointment = null;
		if (params[0] != null) {
			appointment = JsonUtil.parsingAppointmentRequest(params[0]);
		}
		// will return new announcements list
		temp = addNewAppSvc.addNewAnnouncement(appointment);
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
