package jy.kim.lancs.magicdoor.rest.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.rest.dao.RESTMagicDoorDao;

public class RESTPutConfirmAppointmentService {

	public String confirmAppointment(String refValue) {
		// TODO Auto-generated method stub
		Boolean result = null;
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		result = dbPro.confirmAppointment(refValue);

		if (result) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result.toString();
	}

	public ArrayList<AppointmentRespDetailBean> getAppointments(String userName) {
		// TODO Auto-generated method stub
		ArrayList<AppointmentRespDetailBean> result = null;
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		result = dbPro.selectAllAppointmentsOfTheLecturerUser(userName);

		if (result != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

}
