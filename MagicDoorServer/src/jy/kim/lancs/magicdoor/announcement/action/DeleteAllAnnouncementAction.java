package jy.kim.lancs.magicdoor.announcement.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.announcement.service.DeleteAllAnnouncementService;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class DeleteAllAnnouncementAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		String userName = (String) request.getParameter("userName");
		System.out.println("announcer = " + userName);

		DeleteAllAnnouncementService service = new DeleteAllAnnouncementService();

		ActionForward forward = new ActionForward();
		ArrayList<AnnouncementRespBean> announcements= null;
		boolean isAllDeleted = service
				.deleteAllAnnouncement(userName);
		if(isAllDeleted){
			announcements = service.getAnnouncementsOf(userName);
		}
		request.setAttribute("announcementList", announcements);
		forward.setUrl("announcement/announcement_list.jsp");

		return forward;
	}

}
