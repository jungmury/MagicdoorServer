package jy.kim.lancs.magicdoor.announcement.service;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementReqBean;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;

import static jy.kim.lancs.magicdoor.util.DbUtil.*;

public class PostAnnouncementService {

	public boolean writeAnnouncement(AnnouncementReqBean ann) {
		int count = 0;
		boolean updateResult = false;

		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		count = dbPro.insertAnAnnouncement(ann);

		if (count > 0) {
			commit(con);
			updateResult = true;
		} else {
			rollback(con);
		}
		close(con);

		return updateResult;
	}

	public AnnouncementRespBean getCurrentAnnouncement(String announcer) {
		// TODO Auto-generated method stub
	
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);
		
		AnnouncementRespBean announcement = dbPro.selectAnnoucementOf(announcer);

		if (announcement != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return announcement;
	}
}
