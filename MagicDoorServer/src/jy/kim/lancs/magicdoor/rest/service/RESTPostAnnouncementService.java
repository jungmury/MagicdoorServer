package jy.kim.lancs.magicdoor.rest.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.rest.bean.AnnouncementReqBean;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.rest.dao.RESTMagicDoorDao;

public class RESTPostAnnouncementService {

	public boolean addNewAnnouncement(AnnouncementReqBean announcement) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		boolean result = dbPro.insertNewAnnouncement(announcement);

		if (result) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return result;
	}

	public ArrayList<AnnouncementRespBean> getAnnouncement(String userName) {
		// TODO Auto-generated method stub

		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		ArrayList<AnnouncementRespBean> result = dbPro
				.selectAnnouncementsFor(userName);

		if (result != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return result;
	}

}
