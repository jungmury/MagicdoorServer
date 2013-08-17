package jy.kim.lancs.magicdoor.rest.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.rest.dao.RESTMagicDoorDao;

public class RESTDeleteAnnouncementService {

	public String deleteAnAnnouncement(int annNo) {
		// TODO Auto-generated method stub
		Boolean result = null;
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);
		
		result = dbPro.deleteAnAnnouncement(annNo);

		if (result != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result.toString();
	}

	public ArrayList<AnnouncementRespBean> getAnnouncements(String tagOwner) {
		// TODO Auto-generated method stub
		ArrayList<AnnouncementRespBean> result = null;
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);
		
		result = dbPro.selectAnnouncementsFor(tagOwner);

		if (result != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

}
