package jy.kim.lancs.magicdoor.rest.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageBean() {
		// TODO Auto-generated constructor stub
	}
	public int msgNo;
	public Timestamp sentDate;
	public String content;
	public String sender;
	public String receiver;
	public String name;
	public String status;
}
