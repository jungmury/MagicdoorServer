package jy.kim.lancs.magicdoor.announcement.service;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;

import static jy.kim.lancs.magicdoor.util.DbUtil.*;

public class GetAnnouncementService {

	public AnnouncementRespBean getAnnouncementWithAnnNum(
			int annNum) {
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
