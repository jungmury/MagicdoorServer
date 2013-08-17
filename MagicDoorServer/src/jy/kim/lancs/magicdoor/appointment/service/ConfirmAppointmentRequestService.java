package jy.kim.lancs.magicdoor.appointment.service;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;

import static jy.kim.lancs.magicdoor.util.DbUtil.*;

public class ConfirmAppointmentRequestService {

	public boolean confirmAppointmentRquestWith(String refValue) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		boolean isConfirmed = dbPro.updateStatusToConfirmed(refValue);

		if (isConfirmed) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return isConfirmed;
	}

	public AppointmentRespDetailBean getAppointmentWithRefVal(String refValue) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		AppointmentRespDetailBean appointment = dbPro
				.selectAppointmentWith(refValue);

		if (appointment != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return appointment;
	}
}
