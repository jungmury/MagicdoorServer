package jy.kim.lancs.magicdoor.login.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.bean.UserInfoBean;
import jy.kim.lancs.magicdoor.login.dao.MagicDoorLoginSystemDbDao;

public class UpdateAccountInfoService {

	public UserInfoBean doUpdateAccountInfo(UserInfoBean updatedAccountInfo) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorLoginSystemDbDao dbPro = new MagicDoorLoginSystemDbDao(con);

		UserInfoBean loginInfo = dbPro.updateAccountInfo(updatedAccountInfo);

		if (loginInfo != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return loginInfo;
	}
}
