package jy.kim.lancs.magicdoor.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jy.kim.lancs.magicdoor.rest.bean.AnnouncementReqBean;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentReqDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.LogInInfoRespBean;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;
import jy.kim.lancs.magicdoor.rest.bean.NFCTagInfoBean;
import jy.kim.lancs.magicdoor.rest.bean.QueryForAppointments;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;
import jy.kim.lancs.magicdoor.rest.bean.UserAccountInfoBean;
import jy.kim.lancs.magicdoor.rest.bean.WebServiceInfoRespBean;

public class RESTMagicDoorDao {
	private Connection connection;

	public RESTMagicDoorDao(Connection connection) {
		this.connection = connection;
	}

	// return userName and userType
	public LogInInfoRespBean selectUserInfo(String userName, String password) {
		// TODO Auto-generated method stub
		LogInInfoRespBean bean = null;

		PreparedStatement pstmt;
		ResultSet rs;
		String sql = "SELECT user_name, user_type FROM tbl_users WHERE user_name = ? AND password = ?";
		try {

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bean = new LogInInfoRespBean();
				bean.setUserName(rs.getString("user_name"));
				bean.setUserType(rs.getString("user_type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	// return userName, userType
	public LogInInfoRespBean selectUserInfo(String userName) {
		// TODO Auto-generated method stub
		LogInInfoRespBean result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		String sql = "SELECT user_name, user_type FROM tbl_users WHERE user_name = ?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new LogInInfoRespBean();
				result.setUserName(rs.getString("user_name"));
				result.setUserType(rs.getString("user_type"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	// return userName and userType
	public LogInInfoRespBean insertUserInfo(UserAccountInfoBean signUpInfo) {
		// TODO Auto-generated method stub
		int count = 0;
		PreparedStatement pstmt = null;
		LogInInfoRespBean loggedInUserInfo = null;
		// String sql =
		// "INSERT INTO tbl_users(user_name, password, first_name, last_name, email_address, user_type) VALUES (?,?,?,?,?,?)";
		String sql = "INSERT INTO tbl_users(user_name, password, first_name, last_name, email_address, user_type) VALUES ('"
				+ signUpInfo.getUserName()
				+ "', '"
				+ signUpInfo.getPassword()
				+ "', '"
				+ signUpInfo.getFirstName()
				+ "', '"
				+ signUpInfo.getLastName()
				+ "', '"
				+ signUpInfo.geteMailAddress()
				+ "', '"
				+ signUpInfo.getUserType() + "')";

		try {
			pstmt = connection.prepareStatement(sql);

			// pstmt.setString(1, signUpInfo.getUserName());
			// pstmt.setString(2, signUpInfo.getPassword());
			// pstmt.setString(3, signUpInfo.getFirstName());
			// pstmt.setString(4, signUpInfo.getLastName());
			// pstmt.setString(5, signUpInfo.geteMailAddress());
			// pstmt.setString(6, signUpInfo.getUserType());

			count = pstmt.executeUpdate(sql);
			System.out.println("INSERT USER INFO count = " + count);
			if (count > 0) {
				// return logged in information
				loggedInUserInfo = new LogInInfoRespBean();
				loggedInUserInfo.setUserName(signUpInfo.getUserName());
				loggedInUserInfo.setUserType(signUpInfo.getUserType());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return loggedInUserInfo;
	}

	// return account information
	public UserAccountInfoBean selectAccountInfo(String userName) {
		// TODO Auto-generated method stub
		UserAccountInfoBean bean = null;
		PreparedStatement pstmt;
		ResultSet rs;

		String sql = "SELECT * FROM tbl_users WHERE user_name = ?";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bean = new UserAccountInfoBean();
				bean.setUserName(rs.getString("user_name"));
				bean.setPassword(rs.getString("password"));
				bean.setFirstName(rs.getString("first_name"));
				bean.setLastName(rs.getString("last_name"));
				bean.seteMailAddress(rs.getString("email_address"));
				bean.setUserType(rs.getString("user_type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	// update account info and return userName and userType
	public LogInInfoRespBean updateAccountInfo(UserAccountInfoBean signUpInfo) {
		// TODO Auto-generated method stub
		int count = 0;
		LogInInfoRespBean loginInfo = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_users SET password = ?, first_name = ?, last_name = ?, email_address = ? WHERE user_name = ?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, signUpInfo.getPassword());
			pstmt.setString(2, signUpInfo.getFirstName());
			pstmt.setString(3, signUpInfo.getLastName());
			pstmt.setString(4, signUpInfo.geteMailAddress());
			pstmt.setString(5, signUpInfo.getUserName());
			count = pstmt.executeUpdate();
			if (count > 0) {
				// return loginInfo = userName, firstName, userType
				loginInfo = selectUserInfo(signUpInfo.getUserName());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return loginInfo;
	}

	public WebServiceInfoRespBean selectServiceDesc(String tagId) {
		// TODO Auto-generated method stub
		WebServiceInfoRespBean bean = null;
		PreparedStatement pstmt;
		ResultSet rs;

		String sql = "SELECT service_description, tag_owner, first_name, last_name FROM tbl_tags, tbl_users WHERE user_name = tag_owner AND tag_id = ?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, tagId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bean = new WebServiceInfoRespBean();
				bean.setServiceDesc(rs.getString("service_description"));
				bean.setTagOwner(rs.getString("tag_owner"));
				bean.setTagOwnerName(rs.getString("first_name") + " "
						+ rs.getString("last_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	public ArrayList<AnnouncementRespBean> selectAnnouncementsFor(
			String tagOwner) {
		// TODO Auto-generated method stub
		ArrayList<AnnouncementRespBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		String sql = "SELECT * FROM tbl_users, tbl_announcements WHERE user_name = announcer AND announcer = ? ORDER BY ann_no ASC";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, tagOwner);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<AnnouncementRespBean>();
				do {
					AnnouncementRespBean announcement = new AnnouncementRespBean();
					announcement.setAnnouncementNum(rs.getInt("ann_no"));
					announcement.setTitle(rs.getString("title"));
					announcement
							.setAnnouncementContent(rs.getString("content"));
					announcement
							.setWrittenDate(rs.getTimestamp("written_date"));
					announcement.setAnnouncerName(rs
							.getString("tbl_users.first_name")
							+ " "+ rs.getString("tbl_users.last_name"));
					announcement.setUserName(rs
							.getString("tbl_users.user_name"));
					result.add(announcement);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	// delete the announcement with given annNum and return the updated list of
	// announcement, announcer = userName;
	public boolean deleteAnAnnouncement(int annNo) {
		// TODO Auto-generated method stub
		boolean result = false;
		PreparedStatement pstmt;
		String sql = "DELETE FROM tbl_announcements WHERE ann_no = ?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, annNo);

			int count = pstmt.executeUpdate();

			if (count > 0) {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public boolean insertNewAnnouncement(
			AnnouncementReqBean announcement) {
		// TODO Auto-generated method stub
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO tbl_announcements(announcer, content, title, written_date) VALUES (?,?,?,?)";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, announcement.getUserName());
			pstmt.setString(2, announcement.getAnnouncementContent());
			pstmt.setString(3, announcement.getTitle());
			pstmt.setTimestamp(4, announcement.getWrittenDate());
			int count = pstmt.executeUpdate();
			if (count > 0) {
				result  = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public Boolean updateAnnouncement(AnnouncementReqBean announcement) {
		// TODO Auto-generated method stub
		boolean result = false;
		PreparedStatement pstmt = null;
			String sql = "UPDATE tbl_announcements SET title = ?, content = ?, written_date = ?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, announcement.getTitle());
			pstmt.setString(2, announcement.getAnnouncementContent());
			pstmt.setTimestamp(3, announcement.getWrittenDate());
			int count = pstmt.executeUpdate();
			if (count > 0) {
				result  = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<AppointmentRespDetailBean> insertNewAppointmentRequest(
			AppointmentReqDetailBean appointment) {
		// TODO Auto-generated method stub
		int count = 0;

		PreparedStatement pstmt = null;
		ArrayList<AppointmentRespDetailBean> appointments = null;

		String sql = "INSERT INTO tbl_time_info(time_info_ref, date, time, lecturer_user_name) VALUES (?,?,?,?)";

		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, appointment.refValue);
			pstmt.setDate(2, appointment.bookingDate);
			pstmt.setTime(3, appointment.bookingTime);
			pstmt.setString(4, appointment.lecturerUserName);
			count = pstmt.executeUpdate();
			if (count > 0) {
				count = 0;
				sql = "INSERT INTO tbl_appointments(title, description, status, requester_user_name, time_info_ref) VALUES (?,?,?,?,?)";
				pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, appointment.title);
				pstmt.setString(2, appointment.description);
				pstmt.setString(3, appointment.status);
				pstmt.setString(4, appointment.requesterUserName);
				pstmt.setString(5, appointment.refValue);
				count = pstmt.executeUpdate();
				if (count > 0) {
					appointments = selectAllAppointmentsForStudentUser(appointment.requesterUserName);
				}

			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return appointments;
	}

	public ArrayList<AppointmentRespDetailBean> selectAllAppointmentsForStudentUser(
			String userName) {
		// TODO Auto-generated method stub
		ArrayList<AppointmentRespDetailBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		String sql = "SELECT * FROM tbl_time_info, tbl_appointments, tbl_users WHERE tbl_time_info.time_info_ref = tbl_appointments.time_info_ref AND requester_user_name = ? AND tbl_users.user_name = tbl_time_info.lecturer_user_name ORDER BY date ASC, time ASC";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<AppointmentRespDetailBean>();
				do {
					AppointmentRespDetailBean appointment = new AppointmentRespDetailBean();
					appointment.refValue = rs
							.getString("tbl_time_info.time_info_ref");
					appointment.title = rs.getString("title");
					appointment.bookingDate = rs.getDate("date");
					appointment.bookingTime = rs.getTime("time");
					appointment.description = rs.getString("description");
					appointment.name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					appointment.requesterUserName = rs
							.getString("requester_user_name");
					appointment.status = rs.getString("status");
					appointment.note = rs.getString("note");
					result.add(appointment);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteAnAppointmentOfTheUser(String refValue) {
		boolean result = false;
		PreparedStatement pstmt;
		String sql = "DELETE FROM tbl_time_info WHERE time_info_ref = ?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, refValue);

			int count = pstmt.executeUpdate();

			if (count > 0) {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<AppointmentRespDetailBean> selectAllAppointmentsOfTheLecturerUser(
			String userName) {
		// TODO Auto-generated method stub
		ArrayList<AppointmentRespDetailBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		String sql = "SELECT * FROM tbl_time_info, tbl_appointments, tbl_users WHERE tbl_time_info.time_info_ref = tbl_appointments.time_info_ref AND lecturer_user_name = ? AND tbl_users.user_name = tbl_appointments.requester_user_name ORDER BY date ASC, time ASC ";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<AppointmentRespDetailBean>();
				do {
					AppointmentRespDetailBean appointment = new AppointmentRespDetailBean();
					appointment.refValue = rs
							.getString("tbl_time_info.time_info_ref");
					appointment.title = rs.getString("title");
					appointment.bookingDate = rs.getDate("date");
					appointment.bookingTime = rs.getTime("time");
					appointment.description = rs.getString("description");
					appointment.name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					appointment.requesterUserName = rs
							.getString("requester_user_name");
					appointment.status = rs.getString("status");
					appointment.note = rs.getString("note");
					result.add(appointment);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Boolean rejectAppointment(String refValue, String note) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		boolean result = false;
		String sql = "UPDATE tbl_appointments SET status = ?, note = ?  WHERE time_info_ref = ?";

		int count = 0;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "rejected");
			pstmt.setString(2, note);
			pstmt.setString(3, refValue);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (count > 0) {
			result = true;
		}

		return result;
	}

	public Boolean confirmAppointment(String refValue) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		boolean result = false;
		String sql = "UPDATE tbl_appointments SET status = ? WHERE time_info_ref = ?";

		int count = 0;

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "confirmed");
			pstmt.setString(2, refValue);
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (count > 0) {
			result = true;
		}

		return result;
	}

	public ArrayList<AppointmentRespDetailBean> selectAppointmentsOfTheLecturerUserInTheMonth(
			QueryForAppointments query) {
		// TODO Auto-generated method stub
		String[] dateInfo = query.date.toString().split("-");
		String year = dateInfo[0];
		String month = dateInfo[1];
		System.out.println("Year = " + year + " Month = " + month);
		ArrayList<AppointmentRespDetailBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM tbl_time_info, tbl_appointments, tbl_users WHERE tbl_time_info.time_info_ref = tbl_appointments.time_info_ref AND lecturer_user_name = ? AND tbl_users.user_name = tbl_time_info.lecturer_user_name AND MONTH(date) = ? AND YEAR(date) = ? ORDER BY date ASC, time ASC");
			pstmt.setString(1, query.tagOwner);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<AppointmentRespDetailBean>();
				do {
					AppointmentRespDetailBean appointment = new AppointmentRespDetailBean();
					appointment.refValue = rs
							.getString("tbl_time_info.time_info_ref");
					appointment.title = rs.getString("title");
					appointment.bookingDate = rs.getDate("date");
					appointment.bookingTime = rs.getTime("time");
					appointment.description = rs.getString("description");
					appointment.name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					appointment.requesterUserName = rs
							.getString("requester_user_name");
					appointment.note = rs.getString("note");
					appointment.status = rs.getString("status");
					result.add(appointment);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<AppointmentRespDetailBean> selectAppointmentsOfTheLecturerUserOnTheDate(
			QueryForAppointments query) {
		// TODO Auto-generated method stub
		String[] dateInfo = query.date.toString().split("-");
		String year = dateInfo[0];
		String month = dateInfo[1];
		String date = dateInfo[2];
		System.out.println("Year = " + year + " Month = " + month + " Date = "
				+ date);
		ArrayList<AppointmentRespDetailBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM tbl_time_info, tbl_appointments, tbl_users WHERE tbl_time_info.time_info_ref = tbl_appointments.time_info_ref AND lecturer_user_name = ? AND tbl_users.user_name = tbl_appointments.requester_user_name AND DAY(date) = ? AND MONTH(date) = ? AND YEAR(date) = ? ORDER BY date ASC, time ASC");
			pstmt.setString(1, query.tagOwner);
			pstmt.setString(2, date);
			pstmt.setString(3, month);
			pstmt.setString(4, year);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<AppointmentRespDetailBean>();
				do {
					AppointmentRespDetailBean appointment = new AppointmentRespDetailBean();
					appointment.refValue = rs
							.getString("tbl_time_info.time_info_ref");
					appointment.title = rs.getString("title");
					appointment.bookingDate = rs.getDate("date");
					appointment.bookingTime = rs.getTime("time");
					appointment.description = rs.getString("description");
					appointment.name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					appointment.requesterUserName = rs
							.getString("requester_user_name");
					appointment.note = rs.getString("note");
					appointment.status = rs.getString("status");
					result.add(appointment);
				} while (rs.next());
			} else {
				System.out.println("No results returned");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean insertTimeTable(TimeTableBean timeTableInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		PreparedStatement pstmt = null;
		System.out.println("subject = " + timeTableInfo.subject + " time: " + timeTableInfo.time + " day: " + timeTableInfo.day + " lecturer_use_name: " + timeTableInfo.userName);
		String sql = "INSERT INTO tbl_time_table(subject, time, day, lecturer_user_name) VALUES (?,?,?,?)";

		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, timeTableInfo.subject);
			pstmt.setTime(2, timeTableInfo.time);
			pstmt.setInt(3, timeTableInfo.day);
			pstmt.setString(4, timeTableInfo.userName);

			int count = pstmt.executeUpdate();
			if (count > 0) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<TimeTableBean> selectWeekTimeTable(String userName) {
		// TODO Auto-generated method stub
		ArrayList<TimeTableBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;
		String sql = "SELECT * FROM tbl_time_table WHERE lecturer_user_name =? ";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<TimeTableBean>();
				do {
					TimeTableBean timeTable = new TimeTableBean();
					timeTable.id = rs.getInt("id");
					timeTable.subject = rs.getString("subject");
					timeTable.day = rs.getInt("day");
					timeTable.time = rs.getTime("time");
					timeTable.userName = rs.getString("lecturer_user_name");
					result.add(timeTable);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateTimeTable(TimeTableBean timeTableInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_time_table SET subject = ? WHERE lecturer_user_name = ? AND day = ? AND time = ?";
	
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, timeTableInfo.subject);
			pstmt.setString(2, timeTableInfo.userName);
			pstmt.setInt(3, timeTableInfo.day);
			pstmt.setTime(4, timeTableInfo.time);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<TimeTableBean> selectDayTimeTable(String userName, int day) {
		// TODO Auto-generated method stub
		ArrayList<TimeTableBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM tbl_time_table WHERE lecturer_user_name = ? AND day = ?");
			pstmt.setString(1, userName);
			pstmt.setInt(2, day);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<TimeTableBean>();
				do {
					TimeTableBean timeTable = new TimeTableBean();
					timeTable.subject = rs.getString("subject");
					timeTable.day = rs.getInt("day");
					timeTable.time = rs.getTime("time");
					timeTable.userName = rs.getString("lecturer_user_name");
					result.add(timeTable);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<MessageBean> selectAllMessagesOfSender(String sender) {
		// TODO Auto-generated method stub
		ArrayList<MessageBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM tbl_messages, tbl_users WHERE sender = ? AND receiver = tbl_users.user_name");
			pstmt.setString(1, sender);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<MessageBean>();
				do {
					MessageBean msg = new MessageBean();
					msg.msgNo = rs.getInt("message_no");
					msg.content = rs.getString("content");
					msg.sentDate = rs.getTimestamp("sent_date");
					msg.sender = rs.getString("sender");
					msg.name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					msg.receiver = rs.getString("receiver");
					msg.status = rs.getString("status");
					result.add(msg);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<MessageBean> addNewMessage(MessageBean message) {
		// TODO Auto-generated method stub
		ArrayList<MessageBean> result = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO tbl_messages(sent_date, sender, receiver, status, content) VALUES (?,?,?,?,?)";

		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setTimestamp(1, message.sentDate);
			pstmt.setString(2, message.sender);
			pstmt.setString(3, message.receiver);
			pstmt.setString(4, message.status);
			pstmt.setString(5, message.content);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				result = selectAllMessagesOfSender(message.sender);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<MessageBean> selectAllMessagesOfReceiver(String receiver) {
		// TODO Auto-generated method stub
		ArrayList<MessageBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM tbl_messages, tbl_users WHERE receiver = ? AND sender = tbl_users.user_name");
			pstmt.setString(1, receiver);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<MessageBean>();
				do {
					MessageBean msg = new MessageBean();
					msg.msgNo = rs.getInt("message_no");
					msg.content = rs.getString("content");
					msg.sentDate = rs.getTimestamp("sent_date");
					msg.sender = rs.getString("sender");
					msg.name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					msg.receiver = rs.getString("receiver");
					msg.status = rs.getString("status");
					result.add(msg);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<MessageBean> replyMessage(MessageBean message) {
		// TODO Auto-generated method stub
		ArrayList<MessageBean> result = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_messages SET status = ? WHERE message_no = ?";

		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, message.status);
			pstmt.setInt(2, message.msgNo);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("Updated");
				sql = "INSERT INTO tbl_reply_messages(sent_date, content, replied_to_msg_no) VALUES (?,?,?)";
				pstmt = connection.prepareStatement(sql);

				pstmt.setTimestamp(1, message.sentDate);
				pstmt.setString(2, message.content);
				pstmt.setInt(3, message.msgNo);
				count = pstmt.executeUpdate();
				if (count > 0) {
					System.out.println("Inserted");
					result = selectAllMessagesOfReceiver(message.receiver);
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<MessageBean> selectAllSentMessagesOfSender(String sender) {
		// TODO Auto-generated method stub
		ArrayList<MessageBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM tbl_messages, tbl_users WHERE sender = ? AND receiver = tbl_users.user_name");
			pstmt.setString(1, sender);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<MessageBean>();
				do {
					MessageBean msg = new MessageBean();
					msg.msgNo = rs.getInt("message_no");
					msg.content = rs.getString("content");
					msg.sentDate = rs.getTimestamp("sent_date");
					msg.sender = rs.getString("sender");
					msg.name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					msg.receiver = rs.getString("receiver");
					msg.status = rs.getString("status");
					result.add(msg);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<MessageBean> selectAllReceivedMessagesOfSender(
			String sender) {
		// TODO Auto-generated method stub
		ArrayList<MessageBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM tbl_messages, tbl_users, tbl_reply_messages WHERE message_no = replied_to_msg_no AND sender = ? AND receiver = tbl_users.user_name AND status ='replied'");
			pstmt.setString(1, sender);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<MessageBean>();
				do {
					MessageBean msg = new MessageBean();
					msg.msgNo = rs.getInt("message_no");
					msg.content = rs.getString("tbl_reply_messages.content");
					msg.sentDate = rs
							.getTimestamp("tbl_reply_messages.sent_date");
					msg.sender = rs.getString("receiver");
					msg.name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					msg.receiver = rs.getString("sender");
					msg.status = rs.getString("status");
					result.add(msg);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<MessageBean> selectAllSentMessagesOfReceiver(
			String receiver) {
		// TODO Auto-generated method stub
		ArrayList<MessageBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM tbl_messages, tbl_users, tbl_reply_messages WHERE receiver = ? AND sender = tbl_users.user_name AND message_no = replied_to_msg_no");
			pstmt.setString(1, receiver);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<MessageBean>();
				do {
					MessageBean msg = new MessageBean();
					msg.msgNo = rs.getInt("message_no");
					msg.content = rs.getString("tbl_reply_messages.content");
					msg.sentDate = rs
							.getTimestamp("tbl_reply_messages.sent_date");
					msg.sender = rs.getString("receiver");
					msg.name = rs.getString("first_name") + " "
							+ rs.getString("last_name");
					msg.receiver = rs.getString("sender");
					msg.status = rs.getString("status");
					result.add(msg);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<NFCTagInfoBean> insertNewTag(NFCTagInfoBean tag) {
		// TODO Auto-generated method stub
		int count = 0;

		PreparedStatement pstmt = null;
		ArrayList<NFCTagInfoBean> tags = null;

		String sql = "INSERT INTO tbl_tags(tag_id, service_description, tag_owner) VALUES (?,?,?)";

		try {
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, tag.tagId);
			pstmt.setString(2, tag.serviceDescription);
			pstmt.setString(3, tag.tagOwner);
			count = pstmt.executeUpdate();
			if (count > 0) {
				tags = selectAllTagsInforForTheTagOwner(tag.tagOwner);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tags;
	}

	public ArrayList<NFCTagInfoBean> selectAllTagsInforForTheTagOwner(
			String tagOwner) {
		// TODO Auto-generated method stub
		ArrayList<NFCTagInfoBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM tbl_tags WHERE tag_owner = ?");
			pstmt.setString(1, tagOwner);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<NFCTagInfoBean>();
				do {
					NFCTagInfoBean tag = new NFCTagInfoBean();
					tag.tagId = rs.getString("tag_id");
					tag.tagOwner = rs.getString("tag_owner");
					tag.serviceDescription = rs
							.getString("service_description");
					result.add(tag);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public WebServiceInfoRespBean selectTagOwnerNameForQRCode(String tagOwner) {
		// TODO Auto-generated method stub
		WebServiceInfoRespBean bean = null;
		PreparedStatement pstmt;
		ResultSet rs;

		String sql = "SELECT first_name, last_name FROM tbl_users WHERE user_name = ?";

		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, tagOwner);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bean = new WebServiceInfoRespBean();
				bean.setServiceDesc("notavailable");
				bean.setTagOwner(tagOwner);
				bean.setTagOwnerName(rs.getString("first_name") + " "
						+ rs.getString("last_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	
	//Excluding rejected status
	public ArrayList<AppointmentRespDetailBean> selectNotRejectedAppointmentsOfTheLecturerUserInTheWeek(
			QueryForAppointments query) {
		// TODO Auto-generated method stub
		String[] dateInfo = query.date.toString().split("-");
		String year = dateInfo[0];
		String month = dateInfo[1];
		System.out.println("Year = " + year + " Month = " + month);
		ArrayList<AppointmentRespDetailBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = connection.prepareStatement("SELECT * FROM tbl_time_info, tbl_appointments, tbl_users WHERE tbl_time_info.time_info_ref = tbl_appointments.time_info_ref AND lecturer_user_name = ? AND tbl_users.user_name = tbl_appointments.requester_user_name AND MONTH(date) = ? AND YEAR(date) = ? AND Week(date) = Week(?) ORDER BY date ASC, time ASC");
			
			//		.prepareStatement("SELECT * FROM tbl_time_info, tbl_appointments, tbl_users WHERE tbl_time_info.time_info_ref = tbl_appointments.time_info_ref AND lecturer_user_name = ? AND tbl_users.user_name = tbl_time_info.lecturer_user_name AND MONTH(date) = ? AND YEAR(date) = ? AND Week(date) = Week(?) ORDER BY date ASC, time ASC");
			pstmt.setString(1, query.tagOwner);
			pstmt.setString(2, month);
			pstmt.setString(3, year);
			pstmt.setDate(4, query.date);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<AppointmentRespDetailBean>();
				do {
					if(!rs.getString("status").equals("rejected")){
						AppointmentRespDetailBean appointment = new AppointmentRespDetailBean();
						appointment.refValue = rs
								.getString("tbl_time_info.time_info_ref");
						appointment.title = rs.getString("title");
						appointment.bookingDate = rs.getDate("date");
						appointment.bookingTime = rs.getTime("time");
						appointment.description = rs.getString("description");
						appointment.name = rs.getString("first_name") + " "
								+ rs.getString("last_name");
						appointment.requesterUserName = rs
								.getString("requester_user_name");
						appointment.note = rs.getString("note");
						appointment.status = rs.getString("status");
						result.add(appointment);
					}
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public UserAccountInfoBean selectLecturerInfoForTheRef(String refValue) {
		// TODO Auto-generated method stub
		UserAccountInfoBean bean = null;
		PreparedStatement pstmt;
		ResultSet rs;

		String sql = "SELECT * FROM tbl_users, tbl_time_info WHERE time_info_ref = ? AND user_name = lecturer_user_name";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, refValue);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bean = new UserAccountInfoBean();
				bean.setUserName(rs.getString("user_name"));
				bean.setPassword(rs.getString("password"));
				bean.setFirstName(rs.getString("first_name"));
				bean.setLastName(rs.getString("last_name"));
				bean.seteMailAddress(rs.getString("email_address"));
				bean.setUserType(rs.getString("user_type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	

}
