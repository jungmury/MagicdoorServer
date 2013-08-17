package jy.kim.lancs.magicdoor.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.bean.UserInfoBean;
import jy.kim.lancs.magicdoor.login.service.RetrieveAccountInfoService;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class RetrieveAccountInfoAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		
		RetrieveAccountInfoService retriveInfo = new RetrieveAccountInfoService();
		UserInfoBean userInfo = retriveInfo.getUpdateAccountInfo(userName);
		
		ActionForward forward = new ActionForward();
	
		//return logged in user info
		//save into session userName, userType
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", userInfo);
		forward.setUrl("update_account_info_form.jsp");

		return forward;
	}

}
