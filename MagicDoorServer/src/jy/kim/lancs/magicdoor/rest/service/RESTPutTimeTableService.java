package jy.kim.lancs.magicdoor.rest.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;
import jy.kim.lancs.magicdoor.rest.dao.RESTMagicDoorDao;

public class RESTPutTimeTableService {

	public boolean modifyTimeTable(TimeTableBean timeTableInfo) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		boolean bean = dbPro.updateTimeTable(timeTableInfo);

		if (bean) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return bean;
	}

	public ArrayList<TimeTableBean> getTimeTable(String userName) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		ArrayList<TimeTableBean> result = dbPro.selectWeekTimeTable(userName);

		if (result != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

}
