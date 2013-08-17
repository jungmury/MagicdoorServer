package jy.kim.lancs.magicdoor.timetable.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;

public class ManageTimetableService {

	public boolean checkIfExists(TimeTableBean info) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		boolean isExist = dbPro.selectTimetableWith(info);

		if (isExist) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return isExist;
	}

	public boolean modifyTimetable(TimeTableBean bean) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		boolean isExist = dbPro.updateTimetableWith(bean);

		if (isExist) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return isExist;
	}

	public boolean addTimetable(TimeTableBean bean) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		boolean isExist = dbPro.insertTimetableWith(bean);

		if (isExist) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return isExist;
	}

	public ArrayList<TimeTableBean> getTimetableOf(String userName) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		ArrayList<TimeTableBean> timetable = dbPro.selectTimetableOf(userName);

		if (timetable != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return timetable;
	}

	public boolean deleteTimetable(int id) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		boolean isDeleted = dbPro.deleteTimetable(id);

		if (isDeleted) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return isDeleted;
	}
}
