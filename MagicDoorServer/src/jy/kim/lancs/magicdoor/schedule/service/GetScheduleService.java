package jy.kim.lancs.magicdoor.schedule.service;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;

import static jy.kim.lancs.magicdoor.util.DbUtil.*;

public class GetScheduleService {

	public ArrayList<AppointmentRespDetailBean> getAppointmentsOf(
			String userName) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		ArrayList<AppointmentRespDetailBean> appointments = dbPro
				.selectNotRejectedAppointmentsOf(userName);

		if (appointments != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return appointments;
	}

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
