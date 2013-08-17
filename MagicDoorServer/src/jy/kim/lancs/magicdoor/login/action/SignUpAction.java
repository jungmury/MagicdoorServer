package jy.kim.lancs.magicdoor.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.bean.UserInfoBean;
import jy.kim.lancs.magicdoor.login.service.SignUpService;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class SignUpAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailAddress = request.getParameter("eMailAddress");
		String userType = request.getParameter("userType");
		
		
		
		UserInfoBean signUpInfo = new UserInfoBean(userName, password, firstName, lastName, emailAddress, userType);
		SignUpService signUpSvc = new SignUpService();
		
		//returned values are userName, firstName and userType
		
		signUpInfo = signUpSvc.doSignUp(signUpInfo);
		ActionForward forward = new ActionForward();
	
		//return logged in user info
		//save into session
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", signUpInfo);
		forward.setUrl("signup_result.jsp");

		return forward;
	}

}
