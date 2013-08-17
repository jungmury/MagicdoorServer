package jy.kim.lancs.magicdoor.rest.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.rest.bean.LogInInfoRespBean;
import jy.kim.lancs.magicdoor.rest.bean.UserAccountInfoBean;
import jy.kim.lancs.magicdoor.rest.dao.RESTMagicDoorDao;

public class RESTUpdateAccountInfoService {

	public LogInInfoRespBean updateAccountInfo(UserAccountInfoBean signUpInfo) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		LogInInfoRespBean bean = dbPro.updateAccountInfo(signUpInfo);

		if (bean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return bean;
	}

}
