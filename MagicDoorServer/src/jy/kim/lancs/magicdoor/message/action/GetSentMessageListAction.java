package jy.kim.lancs.magicdoor.message.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.message.service.GetSentMessageListService;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class GetSentMessageListAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		
		System.out.println("userName = " + userName);
		
		GetSentMessageListService service = new GetSentMessageListService();
		ArrayList<MessageBean> messages = service.getSentMessageOf(userName);
		ActionForward forward = new ActionForward();
		if (messages != null) {
			request.setAttribute("sentMessageList", messages);
		}
		forward.setUrl("message/sent_message_list.jsp");

		return forward;
	}

}
