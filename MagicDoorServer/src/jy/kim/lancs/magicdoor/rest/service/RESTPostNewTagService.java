package jy.kim.lancs.magicdoor.rest.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.rest.bean.NFCTagInfoBean;
import jy.kim.lancs.magicdoor.rest.dao.RESTMagicDoorDao;

public class RESTPostNewTagService {

	public ArrayList<NFCTagInfoBean> addNewTag(NFCTagInfoBean tag) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RESTMagicDoorDao dbPro = new RESTMagicDoorDao(con);

		ArrayList<NFCTagInfoBean> bean = dbPro.insertNewTag(tag);

		if (bean != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return bean;
	}

}
