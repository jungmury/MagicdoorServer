package jy.kim.lancs.magicdoor.rest.bean;

import java.sql.Timestamp;

public class AnnouncementRespBean {

	private int announcementNum;
	private String title;
	private String announcementContent;
	private Timestamp writtenDate;
	private String announcerName;
	private String userName;

	public AnnouncementRespBean() {
	}

	public String getAnnouncerName() {
		return announcerName;
	}

	public void setAnnouncerName(String announcerName) {
		this.announcerName = announcerName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
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
}
