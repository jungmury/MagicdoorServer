package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.LogInInfoReqBean;
import jy.kim.lancs.magicdoor.rest.bean.LogInInfoRespBean;
import jy.kim.lancs.magicdoor.rest.service.RESTGetLogInService;
import jy.kim.lancs.magicdoor.rest.util.JsonUtil;

public class RESTGetLogInAction implements ActionForRESTWS {
	
	@Override
	public String excute(String... params) {
		String result = null;
		LogInInfoRespBean bean = null;
		
		RESTGetLogInService logInSvc = new RESTGetLogInService();
		ObjectMapper mapper = new ObjectMapper();

		// TODO Auto-generated method stub
		//userName, password
		System.out.println(params[0]);
		LogInInfoReqBean logInInfo = null;
		//get Object from Json value
		if (params[0] != null) {
			logInInfo = JsonUtil.parsingUserNameAndPwd(params[0]);
		}
		bean = logInSvc.tryLogIn(logInInfo);
		
		Writer writer = new StringWriter();
		try {
			// generate json using jacskon mapper
			mapper.writeValue(writer, bean);
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
