package jy.kim.lancs.magicdoor.announcement.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.announcement.service.GetAnnouncementListService;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class GetAnnoucementListAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String announcer = request.getParameter("userName");
		
		System.out.println("announcer = " + announcer);
		
		//update announcement of the logged in user
		GetAnnouncementListService service = new GetAnnouncementListService();
		ArrayList<AnnouncementRespBean> announcements = service.selectAnnouncementsOf(announcer);
		ActionForward forward = new ActionForward();
		if (announcements != null) {
			request.setAttribute("announcementList", announcements);
		}
		forward.setUrl("announcement/announcement_list.jsp");

		return forward;
	}

}
