package jy.kim.lancs.magicdoor.announcement.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.announcement.service.GetAnnouncementService;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class GetAnnoucementAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int annNum = Integer.parseInt((String)request.getParameter("annNum"));
		
		System.out.println("annNum = " + annNum);
		
		//update announcement of the logged in user
		GetAnnouncementService service = new GetAnnouncementService();
		AnnouncementRespBean announcement = service.getAnnouncementWithAnnNum(annNum);
		ActionForward forward = new ActionForward();
		if (announcement != null) {
			request.setAttribute("currentAnnouncement", announcement);
		}
		forward.setUrl("announcement/content.jsp");

		return forward;
	}
	

}
