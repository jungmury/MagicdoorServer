package jy.kim.lancs.magicdoor.rest.bean;

import java.sql.Timestamp;

public class AnnouncementReqBean {
	private int annNum;
	private String title;
	private String announcementContent;
	private Timestamp writtenDate;
	private String userName;

	public AnnouncementReqBean() {
	}

	public void setAnnNum(int annNum) {
		this.annNum = annNum;
	}

	public int getAnnNum() {
		return annNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWrittenDate(Timestamp writtenDate) {
		this.writtenDate = writtenDate;
	}

	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}

	public Timestamp getWrittenDate() {
		return writtenDate;
	}

	public String getAnnouncementContent() {
		return announcementContent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
