package jy.kim.lancs.magicdoor.bean;

import java.sql.Timestamp;

public class AnnouncementBean {

	private int announcementNum;
	private String announcementContent;
	private Timestamp writtenDate;
	private String userName;
	private String title;

	public AnnouncementBean() {
	}

	public AnnouncementBean(String announcementContent, Timestamp writtenDate) {
		this.announcementContent = announcementContent;
		this.writtenDate = writtenDate;
	}

	public AnnouncementBean(String announcementContent, Timestamp writtenDate,
			String userName) {
		this.announcementContent = announcementContent;
		this.writtenDate = writtenDate;
		this.userName = userName;
	}

	public AnnouncementBean(int announcementNum, String announcementContent,
			Timestamp writtenDate) {
		this.announcementNum = announcementNum;
		this.announcementContent = announcementContent;
		this.writtenDate = writtenDate;
	}

	public AnnouncementBean(String announcementContent, Timestamp writtenDate,
			String userName, String title) {
		// TODO Auto-generated constructor stub
		this.announcementContent = announcementContent;
		this.writtenDate = writtenDate;
		this.userName = userName;
		this.title = title;
	}

	public void setAnnouncementNum(int announcementNum) {
		this.announcementNum = announcementNum;
	}

	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}

	public void setWrittenDate(Timestamp writtenDate) {
		this.writtenDate = writtenDate;
	}

	public String getAnnouncementContent() {
		return announcementContent;
	}

	public int getAnnouncementNum() {
		return announcementNum;
	}

	public Timestamp getWrittenDate() {
		return writtenDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
