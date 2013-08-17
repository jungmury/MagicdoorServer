package jy.kim.lancs.magicdoor.login.service;

import static jy.kim.lancs.magicdoor.util.DbUtil.close;
import static jy.kim.lancs.magicdoor.util.DbUtil.commit;
import static jy.kim.lancs.magicdoor.util.DbUtil.getConnection;
import static jy.kim.lancs.magicdoor.util.DbUtil.rollback;

import java.sql.Connection;

import jy.kim.lancs.magicdoor.login.dao.MagicDoorLoginSystemDbDao;

public class CloseAccountService {
	
	public boolean doCloseAccount(String userName) {
		// TODO Auto-generated method stub
		boolean resultForDeleteTheUser = false;
		
		Connection con = getConnection();
		MagicDoorLoginSystemDbDao dbPro = new MagicDoorLoginSystemDbDao(con);

		//when foreign key with cascade option was defined in database
		resultForDeleteTheUser = dbPro.deleteTheUser(userName);
		
		if (resultForDeleteTheUser) {
			commit(con);
		} else {
			rollback(con);
		}
		/*
		 //
		 When foreign key with cascade is not degined on database
		boolean resultForDeleteAnnoucements = false;
		resultForDeleteAnnoucements = dbPro.deleteAllAnnouncementsForTheUser(userName);
		if(resultForDeleteAnnoucements){
			resultForDeleteTheUser = dbPro.deleteTheUser(userName);
		}
		
		if (resultForDeleteAnnoucements && resultForDeleteTheUser) {
			commit(con);
		} else {
			rollback(con);
		}*/
		close(con);

		return resultForDeleteTheUser;
	}
}
