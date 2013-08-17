package jy.kim.lancs.magicdoor.announcement.service;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;

import static jy.kim.lancs.magicdoor.util.DbUtil.*;

public class GetAnnouncementListService {

	public ArrayList<AnnouncementRespBean> selectAnnouncementsOf(String announcer) {
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
