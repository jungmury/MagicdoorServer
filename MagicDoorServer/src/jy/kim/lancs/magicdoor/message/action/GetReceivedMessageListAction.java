package jy.kim.lancs.magicdoor.message.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.message.service.GetReceivedMessageListService;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class GetReceivedMessageListAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		
		System.out.println("userName = " + userName);
		
		GetReceivedMessageListService service = new GetReceivedMessageListService();
		ArrayList<MessageBean> messages = service.getReceivedMessageOf(userName);
		ActionForward forward = new ActionForward();
		if (messages != null) {
			request.setAttribute("receivedMessageList", messages);
		}
		forward.setUrl("message/received_message_list.jsp");

		return forward;
	}

}
