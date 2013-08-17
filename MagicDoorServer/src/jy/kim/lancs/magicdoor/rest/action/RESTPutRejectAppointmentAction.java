package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.QueryForAppointments;
import jy.kim.lancs.magicdoor.rest.service.RESTPutRejectAppointmentService;
import jy.kim.lancs.magicdoor.rest.util.JsonUtil;

public class RESTPutRejectAppointmentAction implements ActionForRESTWS {

	@Override
	public String excute(String... params) {
		// refValue
		QueryForAppointments query = null;
		RESTPutRejectAppointmentService service = 
				new RESTPutRejectAppointmentService();
		ObjectMapper mapper = new ObjectMapper();
		if(params != null){
			query = JsonUtil.parsingQueryForAppointments(params[0]);
		}
		
		System.out.println("refValue = " + query.refValue);
		System.out.println("userName = " + query.tagOwner);
		System.out.println("note = " + query.note);
	
		
		String result = service.rejectAppointment(query.refValue, query.note);

		ArrayList<AppointmentRespDetailBean> temp;

		if (result != null) {
			result = null;
			temp = service.getAppointments(query.tagOwner);
			Writer writer = new StringWriter();
			try {
				if (temp != null) {
					// generate json using jacskon mapper
					mapper.writeValue(writer, temp);
					result = writer.toString();
					System.out.println(result);
				}

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
		return result;
	}
}
