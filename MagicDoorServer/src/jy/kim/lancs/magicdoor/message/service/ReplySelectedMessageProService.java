package jy.kim.lancs.magicdoor.message.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.dao.MagicDoorDao;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;

public class ReplySelectedMessageProService {

	public boolean updateStatusToReply(int msgNo) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		boolean isReplied = dbPro.updateStatusToReply(msgNo);

		if (isReplied) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return isReplied;
	}
	
	public boolean insertRepliedMessage(MessageBean message) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		boolean isInserted = dbPro.insertRepliedMessage(message);

		if (isInserted) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return isInserted;
	}

	public MessageBean getRepliedMessage(int msgNo) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MagicDoorDao dbPro = new MagicDoorDao(con);

		MessageBean message = dbPro.selectRepliedMessage(msgNo);

		if (message != null) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return message;
	}
}
