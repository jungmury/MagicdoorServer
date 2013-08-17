package jy.kim.lancs.magicdoor.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.login.service.CloseAccountService;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class CloseAccountAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		
		CloseAccountService closeAccSvc = new CloseAccountService();
		boolean result = closeAccSvc.doCloseAccount(userName);
		ActionForward forward = new ActionForward();
	
		//return logged in user info
		//save into session
		HttpSession session = request.getSession();
		session.invalidate();
		if(result){
			request.setAttribute("status", "succeed");
		} else {
			request.setAttribute("status", "failed");
		}
		forward.setUrl("close_account_result.jsp");

		return forward;
	}

}
