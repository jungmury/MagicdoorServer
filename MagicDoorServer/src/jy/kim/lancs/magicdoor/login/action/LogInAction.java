package jy.kim.lancs.magicdoor.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.bean.UserInfoBean;
import jy.kim.lancs.magicdoor.login.service.LoginService;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class LogInAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String loginId = request.getParameter("userName");
		String password = request.getParameter("password");
		
		UserInfoBean loginInfo = new UserInfoBean(loginId, password);

		LoginService loginSvc = new LoginService();
		
		//returned values are userName, firstName and userType
		UserInfoBean loggedInUserInfo = loginSvc.doLogin(loginInfo);
		
		ActionForward forward = new ActionForward();
	
		//return logged in user info
		//save into session
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", loggedInUserInfo);
		
		forward.setUrl("login_result.jsp");

		return forward;
	}

}
