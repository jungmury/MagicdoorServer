package jy.kim.lancs.magicdoor.login.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.bean.UserInfoBean;
import jy.kim.lancs.magicdoor.login.dao.MagicDoorLoginSystemDbDao;

public class RetrieveAccountInfoService {

	public UserInfoBean getUpdateAccountInfo(String userName) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorLoginSystemDbDao dbPro = new MagicDoorLoginSystemDbDao(con);

		UserInfoBean userInfo = dbPro.selectAccountInfo(userName);

		if (userInfo != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return userInfo;
	}

}
