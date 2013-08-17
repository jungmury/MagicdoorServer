package jy.kim.lancs.magicdoor.announcement.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.announcement.service.DeleteAnnouncementService;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class DeleteAnnouncementAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		String userName = (String)request.getParameter("userName");
		int annNum = Integer.parseInt((String)request.getParameter("annNum"));
		System.out.println("annNum = " + annNum);
		System.out.println("announcer = " + userName);
		
		DeleteAnnouncementService service = new DeleteAnnouncementService();
		boolean isDeleted = service.deleteAnnouncement(annNum);
		
		ActionForward forward = new ActionForward();
		if (isDeleted) {
			ArrayList<AnnouncementRespBean> announcements = service.getAnnouncementsOf(userName);
			request.setAttribute("announcementList", announcements);
		}
		forward.setUrl("announcement/announcement_list.jsp");

		return forward;
	}

}
