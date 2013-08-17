package jy.kim.lancs.magicdoor.rest.action;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jy.kim.lancs.magicdoor.actioninterface.ActionForRESTWS;
import jy.kim.lancs.magicdoor.rest.bean.LogInInfoRespBean;
import jy.kim.lancs.magicdoor.rest.bean.UserAccountInfoBean;
import jy.kim.lancs.magicdoor.rest.service.RESTPutSignUpService;
import jy.kim.lancs.magicdoor.rest.util.JsonUtil;
public class RESTPutSignUpAction implements ActionForRESTWS {

	@Override
	public String excute(String... params) {
		// TODO Auto-generated method stub
		String result = null;
		LogInInfoRespBean temp = null;
		RESTPutSignUpService signUpSvc = new RESTPutSignUpService();
		ObjectMapper mapper = new ObjectMapper();

		//userInfo
		System.out.println(params[0]);
		UserAccountInfoBean signUpInfo = null;
		if (params[0] != null) {
			signUpInfo = JsonUtil.parsingSignUpInfo(params[0]);
		}
		temp = signUpSvc.signUp(signUpInfo);
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
