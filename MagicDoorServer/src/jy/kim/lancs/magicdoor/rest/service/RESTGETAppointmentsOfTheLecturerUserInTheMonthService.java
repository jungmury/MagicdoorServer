package jy.kim.lancs.magicdoor.rest.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.QueryForAppointments;
import jy.kim.lancs.magicdoor.rest.dao.RESTMagicDoorDao;

public class RESTGETAppointmentsOfTheLecturerUserInTheMonthService {

	public ArrayList<AppointmentRespDetailBean> getAppointmentsOfTheTagOwnerInTheGivenMonth(
			QueryForAppointments query) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		ArrayList<AppointmentRespDetailBean> bean = dbPro.selectAppointmentsOfTheLecturerUserInTheMonth(query);

		if (bean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return bean;
	}
}
