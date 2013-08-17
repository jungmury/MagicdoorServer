package jy.kim.lancs.magicdoor.rest.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.rest.bean.LogInInfoReqBean;
import jy.kim.lancs.magicdoor.rest.bean.LogInInfoRespBean;
import jy.kim.lancs.magicdoor.rest.dao.RESTMagicDoorDao;


public class RESTGetLogInService {

	public LogInInfoRespBean tryLogIn(LogInInfoReqBean logInInfo) {
		// TODO Auto-generated method stub
		
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		LogInInfoRespBean bean = dbPro.selectUserInfo(logInInfo.getUserName());

		if (bean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return bean;
	}
	

}
