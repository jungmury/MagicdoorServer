package jy.kim.lancs.magicdoor.message.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;

public class ReplySelectedMessageService {

		public MessageBean getSelectedReceivedMessageWith(int msgNo) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);
		
		MessageBean message = dbPro.selectReceivedMessageWith(msgNo);

		if (message != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return message;
	}
}
