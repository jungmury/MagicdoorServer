package jy.kim.lancs.magicdoor.announcement.action;

import static jy.kim.lancs.magicdoor.util.TimeUtilForMagicdoor.getTodayTimestamp;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jy.kim.lancs.magicdoor.actioninterface.Action;
import jy.kim.lancs.magicdoor.announcement.service.PostAnnouncementService;
import jy.kim.lancs.magicdoor.bean.UserInfoBean;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementReqBean;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.vo.ActionForward;

public class PostAnnoucementAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub

		
		//get userInfo from session to get user_name and update with announcement
		HttpSession session = request.getSession();
		UserInfoBean loggedInUserInfo= (UserInfoBean)session.getAttribute("userInfo");
		String announcer = loggedInUserInfo.getUserName();
		
		String content = (String)request.getParameter("announcementContent");
		String title = (String)request.getParameter("announcementTitle");
		Timestamp writenDate = getTodayTimestamp();
		
		System.out.println("announcer = " + announcer);
		
		//update announcement of the logged in user
		AnnouncementReqBean announcement = new AnnouncementReqBean();
		announcement.setTitle(title);
		announcement.setAnnouncementContent(content);
		announcement.setWrittenDate(writenDate);
		announcement.setUserName(announcer);
		PostAnnouncementService writeSvc = new PostAnnouncementService();
		boolean updateResult = writeSvc.writeAnnouncement(announcement);
		

		ActionForward forward = new ActionForward();
		if (updateResult) {
			AnnouncementRespBean curAnnouncement = writeSvc.getCurrentAnnouncement(announcer);
			request.setAttribute("currentAnnouncement", curAnnouncement);
		}
		forward.setUrl("announcement/content.jsp");

		return forward;
	}

}
