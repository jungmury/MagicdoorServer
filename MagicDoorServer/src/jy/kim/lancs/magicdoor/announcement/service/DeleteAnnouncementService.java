package jy.kim.lancs.magicdoor.announcement.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;

public class DeleteAnnouncementService {

	public boolean deleteAnnouncement(int annNum) {
		boolean updateResult = false;

		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		updateResult = dbPro.deleteAnnouncementWithAnnNum(annNum);

		if (updateResult) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return updateResult;
	}

	public ArrayList<AnnouncementRespBean> getAnnouncementsOf(String announcer) {
		// TODO Auto-generated method stub
	
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);
		ArrayList<AnnouncementRespBean> announcements = dbPro.selectAnnoucementsOf(announcer);

		if (announcements != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return announcements;
	}
}
