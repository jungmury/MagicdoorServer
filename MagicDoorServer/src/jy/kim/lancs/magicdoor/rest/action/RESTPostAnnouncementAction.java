package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementReqBean;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.rest.service.RESTPostAnnouncementService;
import jy.kim.lancs.magicdoor.rest.util.JsonUtil;

public class RESTPostAnnouncementAction implements ActionForRESTWS{

	@Override
	public String excute(String... params) {
		// TODO Auto-generated method stub
		String result = null;
		ArrayList<AnnouncementRespBean> temp = null;
		Writer writer = null;
		RESTPostAnnouncementService service = 
				new RESTPostAnnouncementService();
		ObjectMapper mapper = new ObjectMapper();

		//new announcement
		System.out.println("new announcement json = " + params[0]);
		AnnouncementReqBean announcement = null;
		if (params[0] != null) {
			announcement = JsonUtil.parsingNewAnnouncement(params[0]);
		}
		// will return announcements list after inserting new announcement
		boolean isInserted = service.addNewAnnouncement(announcement);
		if(isInserted){
			temp = 	service.getAnnouncement(announcement.getUserName());
			writer = new StringWriter();
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
		}
		
		return result;
	}

}
