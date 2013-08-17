package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;
import jy.kim.lancs.magicdoor.rest.service.RESTPutTimeTableService;
import jy.kim.lancs.magicdoor.rest.util.JsonUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class RESTPutTimeTableAction implements ActionForRESTWS {

	@Override
	public String excute(String... params) {
		// TODO Auto-generated method stub
		String result = null;
		ArrayList<TimeTableBean> temp = null;
		RESTPutTimeTableService service = new RESTPutTimeTableService();
		ObjectMapper mapper = new ObjectMapper();

		// userInfo
		System.out.println("timetable " + params[0]);
		TimeTableBean timeTableInfo = null;
		if (params[0] != null) {
			timeTableInfo = JsonUtil.parsingTimeTableInfo(params[0]);
			if (service.modifyTimeTable(timeTableInfo)) {
				temp = service.getTimeTable(timeTableInfo.userName);
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
				System.out.println("Inserting new time table failed");
			}
		} else {
			System.out.println("Parsing failed");
		}

		return result;
	}

}
