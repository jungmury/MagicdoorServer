package jy.kim.lancs.magicdoor.announcement.service;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementReqBean;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;

import static jy.kim.lancs.magicdoor.util.DbUtil.*;

public class UpdateAnnouncementProService {

	public boolean updateAnnouncement(AnnouncementReqBean ann) {
		int count = 0;
		boolean updateResult = false;

		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		count = dbPro.updateAnnouncement(ann);

		if (count > 0) {
			commit(con);
			updateResult = true;
		} else {
			rollback(con);
		}
		close(con);

		return updateResult;
	}

	public AnnouncementRespBean getUpdatedAnnouncement(int annNum) {
		// TODO Auto-generated method stub
	
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);
		
		AnnouncementRespBean announcement = dbPro.selectAnnouncementWithAnnNum(annNum);

		if (announcement != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return announcement;
	}
}
