package jy.kim.lancs.magicdoor.message.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.message.service.GetSelectedSentMessageService;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class GetSelectedSentMessageAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int msgNo = Integer.parseInt((String)request.getParameter("msgNo"));
		
		System.out.println("msgNo = " + msgNo);
		
		GetSelectedSentMessageService service = new GetSelectedSentMessageService();
		MessageBean message = service.getSelectedSentMessageWith(msgNo);
		ActionForward forward = new ActionForward();
		if (message != null) {
			request.setAttribute("message", message);
		}
		forward.setUrl("message/sent_message_content.jsp");

		return forward;
	}

}
