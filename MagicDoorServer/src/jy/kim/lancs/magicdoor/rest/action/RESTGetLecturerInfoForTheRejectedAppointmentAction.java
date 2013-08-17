package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.UserAccountInfoBean;
import jy.kim.lancs.magicdoor.rest.service.RESTGetLecturerInfoForTheRejectedAppointmentService;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class RESTGetLecturerInfoForTheRejectedAppointmentAction implements ActionForRESTWS{

	@Override
	public String excute(String... params) {
		// TODO Auto-generated method stub
		String result = null;
		UserAccountInfoBean temp = null;
		RESTGetLecturerInfoForTheRejectedAppointmentService accInfoSvc = new RESTGetLecturerInfoForTheRejectedAppointmentService();
		ObjectMapper mapper = new ObjectMapper();
		
		//userName
		System.out.println(params[0]);
		temp = accInfoSvc.getLecturerInfo(params[0]);
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
