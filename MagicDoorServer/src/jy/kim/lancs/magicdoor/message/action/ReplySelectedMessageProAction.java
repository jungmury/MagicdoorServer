package jy.kim.lancs.magicdoor.message.action;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.message.service.ReplySelectedMessageProService;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class ReplySelectedMessageProAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int msgNo = Integer.parseInt((String) request.getParameter("msgNo"));
		String msg = (String) request.getParameter("repliedMessage");
		System.out.println("msgNo = " + msgNo);
		System.out.println("msg = " + msg);

		MessageBean repliedMessage = new MessageBean();
		repliedMessage.msgNo = msgNo;
		repliedMessage.content = msg;
		repliedMessage.sentDate = new Timestamp(Calendar.getInstance()
				.getTimeInMillis());

		ReplySelectedMessageProService service = new ReplySelectedMessageProService();

		boolean isReplied = service.updateStatusToReply(msgNo);
		ActionForward forward = new ActionForward();

		if (isReplied) {
			boolean isInserted = service.insertRepliedMessage(repliedMessage);

			if (isInserted) {
				MessageBean message = service.getRepliedMessage(msgNo);
				request.setAttribute("message", message);
			}
		}
		forward.setUrl("message/reply_message_content.jsp");

		return forward;
	}

}
