package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.WebServiceInfoRespBean;
import jy.kim.lancs.magicdoor.rest.service.RESTGetWebServiceForQRCodeService;

public class RESTGetWebServiceForQRCodeAction implements ActionForRESTWS {

	@Override
	public String excute(String... params) {
		// TODO Auto-generated method stub
		String result = null;
		WebServiceInfoRespBean temp = null;
		RESTGetWebServiceForQRCodeService reqWebSvc = new RESTGetWebServiceForQRCodeService();
		ObjectMapper mapper = new ObjectMapper();
		
		//tagId
		System.out.println(params[0]);
		temp = reqWebSvc.getServiceDesc(params[0]);
		Writer writer = new StringWriter();
		try {
			// generate json using jacskon mapper
			mapper.writeValue(writer, temp);
			result = writer.toString();
			System.out.println(result);

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
