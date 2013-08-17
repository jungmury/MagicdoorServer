package jy.kim.lancs.magicdoor.rest.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.rest.bean.LogInInfoRespBean;
import jy.kim.lancs.magicdoor.rest.dao.RESTMagicDoorDao;

public class RESTGetDuplicateIDService {

	public LogInInfoRespBean getLoginInfo(String userName) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		LogInInfoRespBean bean = dbPro.selectUserInfo(userName);

		if (bean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return bean;
	}

}
