package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;
import jy.kim.lancs.magicdoor.rest.service.RESTPostNewMessageService;
import jy.kim.lancs.magicdoor.rest.util.JsonUtil;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class RESTPostNewMessageAction implements ActionForRESTWS {

	@Override
	public String excute(String... params) {
		// TODO Auto-generated method stub
		String result = null;
		ArrayList<MessageBean> temp = null;
		RESTPostNewMessageService service = new RESTPostNewMessageService();
		ObjectMapper mapper = new ObjectMapper();

		// annNo
		System.out.println(params[0]);
		MessageBean message = null;
		if (params[0] != null) {
			message = JsonUtil.parsingNewMessage(params[0]);
		}
		// will return new announcements list
		temp = service.addNewMessage(message);
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
