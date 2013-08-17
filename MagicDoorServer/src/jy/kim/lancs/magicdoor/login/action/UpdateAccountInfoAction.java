package jy.kim.lancs.magicdoor.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.bean.UserInfoBean;
import jy.kim.lancs.magicdoor.login.service.UpdateAccountInfoService;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class UpdateAccountInfoAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailAddress = request.getParameter("eMailAddress");
		
		UserInfoBean updatedAccountInfo = new UserInfoBean(userName, password, firstName, lastName, emailAddress);
		
		UpdateAccountInfoService updateAccountInfoSvc = new UpdateAccountInfoService();
		UserInfoBean loginInfo = updateAccountInfoSvc.doUpdateAccountInfo(updatedAccountInfo);
		
		ActionForward forward = new ActionForward();
	
		//return logged in user info
		//save into session userName, userType
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", loginInfo);
		forward.setUrl("update_account_info_result.jsp");

		return forward;
	}

}
