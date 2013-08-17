package jy.kim.lancs.magicdoor.timetable.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;

public class GetTimetableService {

	public ArrayList<TimeTableBean> getTimetableOf(String userName) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);
		
		ArrayList<TimeTableBean> timeTable = dbPro.selectTimetableOf(userName);

		if (timeTable != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return timeTable;
	}
}
