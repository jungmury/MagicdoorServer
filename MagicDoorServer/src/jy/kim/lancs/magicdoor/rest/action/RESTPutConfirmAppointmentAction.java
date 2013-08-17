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
import jy.kim.lancs.magicdoor.rest.service.RESTPutConfirmAppointmentService;

public class RESTPutConfirmAppointmentAction implements ActionForRESTWS {

	@Override
	public String excute(String... params) {
		// refValue
		System.out.println("refValue = " + params[0]);
		System.out.println("userName = " + params[1]);
		RESTPutConfirmAppointmentService service = new RESTPutConfirmAppointmentService();
		ObjectMapper mapper = new ObjectMapper();
		String result = service.confirmAppointment(params[0]);

		ArrayList<AppointmentRespDetailBean> temp;
		if (result != null) {
			result = null;
			temp = service.getAppointments(params[1]);
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
