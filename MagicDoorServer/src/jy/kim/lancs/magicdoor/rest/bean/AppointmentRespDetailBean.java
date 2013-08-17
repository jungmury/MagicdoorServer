package jy.kim.lancs.magicdoor.rest.bean;

import java.sql.Date;
import java.sql.Time;

public class AppointmentRespDetailBean {
	public String refValue;
	public String title;
	public String name; // lecturer's username received and send
								// lecturer's real name back
	public String requesterUserName;
	public String description;
	public Date bookingDate;
	public Time bookingTime;
	public String status;
	public String note;
	public int duration;
}
