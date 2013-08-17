package jy.kim.lancs.magicdoor.message.service;

import java.sql.Connection;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;

import static jy.kim.lancs.magicdoor.util.DbUtil.*;

public class GetReceivedMessageListService {

	public ArrayList<MessageBean> getReceivedMessageOf(
			String userName) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);
		
		ArrayList<MessageBean> messages = dbPro.selectReceivedMessageOf(userName);

		if (messages != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return messages;
	}
}
