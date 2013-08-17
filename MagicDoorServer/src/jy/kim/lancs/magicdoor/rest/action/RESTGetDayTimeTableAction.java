package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;
import jy.kim.lancs.magicdoor.rest.service.RESTGetDayTimeTableService;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class RESTGetDayTimeTableAction implements ActionForRESTWS {

	@Override
	public String excute(String... params) {
		// TODO Auto-generated method stub
		String result = null;
		ArrayList<TimeTableBean> temp = null;
		RESTGetDayTimeTableService service = new RESTGetDayTimeTableService();
		ObjectMapper mapper = new ObjectMapper();

		// userName and day--> shoud change to int
		int day = Integer.parseInt(params[1]);
		System.out.println(params[0]);
		if (params[0] != null) {
			temp = service.getDayTimeTable(params[0], day);
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
		} else {
			System.out.println("Getting time table failed");
		}

		return result;
	}

}
