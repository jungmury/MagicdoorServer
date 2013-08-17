package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.rest.service.RESTDeleteAnnouncementService;

public class RESTDeleteAnnouncementAction implements ActionForRESTWS {

	@Override
	public String excute(String... params) {
		System.out.println("annNo = " + Integer.parseInt(params[0]));
		System.out.println("tagOwner = " + params[1]);
		RESTDeleteAnnouncementService service = new RESTDeleteAnnouncementService();
		ObjectMapper mapper = new ObjectMapper();
		String result = service.deleteAnAnnouncement(Integer
				.parseInt(params[0]));
		ArrayList<AnnouncementRespBean> temp;
		if (result != null) {
			result = null;
			temp = service.getAnnouncements(params[1]);
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
