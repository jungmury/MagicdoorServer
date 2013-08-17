package jy.kim.lancs.magicdoor.rest.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.rest.bean.UserAccountInfoBean;
import jy.kim.lancs.magicdoor.rest.dao.RESTMagicDoorDao;

public class RESTGetLecturerInfoForTheRejectedAppointmentService {

	public UserAccountInfoBean getLecturerInfo(String refValue) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		UserAccountInfoBean bean = dbPro.selectLecturerInfoForTheRef(refValue);

		if (bean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return bean;
	}

}
