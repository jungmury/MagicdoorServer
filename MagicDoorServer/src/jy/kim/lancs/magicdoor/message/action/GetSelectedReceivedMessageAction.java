package jy.kim.lancs.magicdoor.message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.message.service.GetSelectedReceivedMessageService;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class GetSelectedReceivedMessageAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int msgNo = Integer.parseInt((String)request.getParameter("msgNo"));
		
		System.out.println("userName = " + msgNo);
		
		GetSelectedReceivedMessageService service = new GetSelectedReceivedMessageService();
		MessageBean message = service.getSelectedReceivedMessageWith(msgNo);
		ActionForward forward = new ActionForward();
		if (message != null) {
			request.setAttribute("message", message);
		}
		forward.setUrl("message/content.jsp");

		return forward;
	}

}
