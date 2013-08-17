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
import jy.kim.lancs.magicdoor.rest.service.RESTGetAnnouncementsService;

public class RESTGetAnnouncementsAction implements ActionForRESTWS {

	@Override
	public String excute(String... params) {
		// TODO Auto-generated method stub
		String result = null;
		ArrayList<AnnouncementRespBean> temp = null;
		RESTGetAnnouncementsService showAnnSvc = new RESTGetAnnouncementsService();
		ObjectMapper mapper = new ObjectMapper();

		//tagOwner
		System.out.println("lectuerer user name = " + params[0]);
		temp = showAnnSvc.getAnnouncementsForTheTagOwner(params[0]);
		Writer writer = new StringWriter();
		try {
			if (temp != null) {
				System.out.println("size =" + temp.size());
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
		return result;
	}

}
