package jy.kim.lancs.magicdoor.announcement.action;

import static jy.kim.lancs.magicdoor.util.TimeUtilForMagicdoor.getTodayTimestamp;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.announcement.service.UpdateAnnouncementProService;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementReqBean;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class UpdateAnnouncementProAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		
		//get userInfo from session to get user_name and update with announcement
		String announcer =  (String)request.getParameter("userName");
		String content = (String)request.getParameter("announcementContent");
		String title = (String)request.getParameter("announcementTitle");
		int annNum = Integer.parseInt((String)request.getParameter("annNum"));
		Timestamp writenDate = getTodayTimestamp();
		
		System.out.println("announcer = " + announcer);
		
		//update announcement of the logged in user
		AnnouncementReqBean announcement = new AnnouncementReqBean();
		announcement.setAnnNum(annNum);
		announcement.setTitle(title);
		announcement.setAnnouncementContent(content);
		announcement.setWrittenDate(writenDate);
		announcement.setUserName(announcer);
		UpdateAnnouncementProService updateSvc = new UpdateAnnouncementProService();
		boolean updateResult = updateSvc.updateAnnouncement(announcement);
		

		ActionForward forward = new ActionForward();
		if (updateResult) {
			AnnouncementRespBean curAnnouncement = updateSvc.getUpdatedAnnouncement(annNum);
			request.setAttribute("currentAnnouncement", curAnnouncement);
		}
		forward.setUrl("announcement/content.jsp");

		return forward;
	}

}
