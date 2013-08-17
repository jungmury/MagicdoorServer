package jy.kim.lancs.magicdoor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static jy.kim.lancs.magicdoor.util.DbUtil.*;
import jy.kim.lancs.magicdoor.bean.AnnouncementBean;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementReqBean;
import jy.kim.lancs.magicdoor.rest.bean.AnnouncementRespBean;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentRespDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;

public class MagicDoorDao {

	private Connection con;

	public MagicDoorDao(Connection con) {
		this.con = con;
	}

	public int insertAnAnnouncement(AnnouncementReqBean ann) {
		// TODO Auto-generated method stub
		int count = 0;

		PreparedStatement pstmt = null;

		String sql = "INSERT INTO tbl_announcements(written_date, content, announcer, title) VALUES (?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setTimestamp(1, ann.getWrittenDate());
			pstmt.setString(2, ann.getAnnouncementContent());
			pstmt.setString(3, ann.getUserName());
			pstmt.setString(4, ann.getTitle());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public AnnouncementBean selectLastAnnoucement() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AnnouncementBean ann = null;

		/*
		 * return last record SELECT * FROM table_name ORDER BY id DESC LIMIT 1
		 */
		String sql = "SELECT * FROM tbl_announcements ORDER BY ann_no DESC LIMIT 1";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ann = new AnnouncementBean();
				ann.setAnnouncementNum(rs.getInt("ann_no"));
				ann.setWrittenDate(rs.getTimestamp("written_date"));
				ann.setAnnouncementContent(rs.getString("content"));
				ann.setTitle(rs.getString("title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return ann;
	}

	public AnnouncementBean selectLastAnnoucement(String lecturerName) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AnnouncementBean ann = null;

		/*
		 * return last record SELECT * FROM table_name ORDER BY id DESC LIMIT 1
		 */
		String sql = "SELECT * FROM tbl_announcements WHERE announcer = ? ORDER BY ann_no DESC LIMIT 1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecturerName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ann = new AnnouncementBean();
				ann.setAnnouncementNum(rs.getInt("ann_no"));
				ann.setWrittenDate(rs.getTimestamp("written_date"));
				ann.setAnnouncementContent(rs.getString("content"));
				ann.setTitle(rs.getString("title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return ann;
	}

	public AnnouncementRespBean selectAnnoucementOf(String lecturerName) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AnnouncementRespBean ann = null;

		/*
		 * return last record SELECT * FROM table_name ORDER BY id DESC LIMIT 1
		 */
		String sql = "SELECT * FROM tbl_announcements WHERE announcer = ? ORDER BY ann_no DESC LIMIT 1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecturerName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ann = new AnnouncementRespBean();
				ann.setAnnouncementNum(rs.getInt("ann_no"));
				ann.setWrittenDate(rs.getTimestamp("written_date"));
				ann.setAnnouncementContent(rs.getString("content"));
				ann.setTitle(rs.getString("title"));
				ann.setUserName(rs.getString("announcer"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return ann;
	}

	public int updateAnnouncement(AnnouncementReqBean ann) {
		// TODO Auto-generated method stub
		int count = 0;

		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_announcements SET written_date = ?, content = ?, title = ? WHERE ann_no = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setTimestamp(1, ann.getWrittenDate());
			pstmt.setString(2, ann.getAnnouncementContent());
			pstmt.setString(3, ann.getTitle());
			pstmt.setInt(4, ann.getAnnNum());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public ArrayList<AnnouncementRespBean> selectAnnoucementsOf(String announcer) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AnnouncementRespBean> announcements = null;
		AnnouncementRespBean announcement = null;

		/*
		 * return last record SELECT * FROM table_name ORDER BY id DESC LIMIT 1
		 */
		String sql = "SELECT * FROM tbl_announcements WHERE announcer = ? ORDER BY ann_no";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, announcer);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				announcements = new ArrayList<AnnouncementRespBean>();
				do {
					announcement = new AnnouncementRespBean();
					announcement.setAnnouncementNum(rs.getInt("ann_no"));
					announcement
							.setWrittenDate(rs.getTimestamp("written_date"));
					announcement
							.setAnnouncementContent(rs.getString("content"));
					announcement.setTitle(rs.getString("title"));
					announcement.setUserName(rs.getString("announcer"));
					announcements.add(announcement);
				} while (rs.next());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return announcements;
	}

	public AnnouncementRespBean selectAnnouncementWithAnnNum(int annNum) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AnnouncementRespBean announcement = null;
		String sql = "SELECT * FROM tbl_announcements WHERE ann_no = ? ORDER BY ann_no DESC LIMIT 1";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, annNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				announcement = new AnnouncementRespBean();
				announcement.setAnnouncementNum(rs.getInt("ann_no"));
				announcement.setWrittenDate(rs.getTimestamp("written_date"));
				announcement.setAnnouncementContent(rs.getString("content"));
				announcement.setTitle(rs.getString("title"));
				announcement.setUserName(rs.getString("announcer"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return announcement;
	}

	public boolean deleteAnnouncementWithAnnNum(int annNum) {
		// TODO Auto-generated method stub

		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM tbl_announcements WHERE ann_no = ?";
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, annNum);
			count = pstmt.executeUpdate();
			if (count > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public boolean deleteAllAnnouncementOf(String userName) {
		// TODO Auto-generated method stub
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM tbl_announcements WHERE announcer = ?";
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			count = pstmt.executeUpdate();
			if (count > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<AppointmentRespDetailBean> selectNotRejectedAppointmentsOf(
			String userName) {
		// TODO Auto-generated method stub
		ArrayList<AppointmentRespDetailBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		String sql = "SELECT * FROM tbl_time_info, tbl_appointments, tbl_users WHERE tbl_time_info.time_info_ref = tbl_appointments.time_info_ref AND lecturer_user_name = ? AND tbl_users.user_name = tbl_appointments.requester_user_name ORDER BY date ASC, time ASC ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = new ArrayList<AppointmentRespDetailBean>();
				do {
					if (!rs.getString("status").equals("rejected")) {
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
					}
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public AppointmentRespDetailBean selectAppointmentWith(String refValue) {
		// TODO Auto-generated method stub
		AppointmentRespDetailBean appointment = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = con
					.prepareStatement("SELECT * FROM tbl_time_info, tbl_appointments, tbl_users WHERE tbl_time_info.time_info_ref = tbl_appointments.time_info_ref AND tbl_appointments.time_info_ref  = ? AND tbl_users.user_name = tbl_appointments.requester_user_name ORDER BY date ASC, time ASC");
			pstmt.setString(1, refValue);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				appointment = new AppointmentRespDetailBean();
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

			} else {
				System.out.println("No results returned");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appointment;
	}

	public boolean updateStatusToConfirmed(String refValue) {
		// TODO Auto-generated method stub
		int count = 0;
		boolean isConfirmed = false;

		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_appointments SET status = ? WHERE time_info_ref = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "confirmed");
			pstmt.setString(2, refValue);

			count = pstmt.executeUpdate();
			if (count > 0) {
				isConfirmed = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return isConfirmed;
	}

	public boolean updateStatusToRejected(String refValue, String note) {
		// TODO Auto-generated method stub
		int count = 0;
		boolean isConfirmed = false;

		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_appointments SET status = ?, note = ? WHERE time_info_ref = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "rejected");
			pstmt.setString(2, note);
			pstmt.setString(3, refValue);

			count = pstmt.executeUpdate();
			if (count > 0) {
				isConfirmed = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return isConfirmed;
	}

	public ArrayList<MessageBean> selectReceivedMessageOf(String userName) {
		// TODO Auto-generated method stub
		ArrayList<MessageBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = con
					.prepareStatement("SELECT * FROM tbl_messages, tbl_users WHERE receiver = ? AND sender = tbl_users.user_name");
			pstmt.setString(1, userName);
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

	public MessageBean selectReceivedMessageWith(int msgNo) {
		// TODO Auto-generated method stub
		MessageBean message = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = con
					.prepareStatement("SELECT * FROM tbl_messages, tbl_users WHERE message_no = ? AND sender = tbl_users.user_name");
			pstmt.setInt(1, msgNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				message = new MessageBean();
				message.msgNo = rs.getInt("message_no");
				message.content = rs.getString("content");
				message.sentDate = rs.getTimestamp("sent_date");
				message.sender = rs.getString("sender");
				message.name = rs.getString("first_name") + " "
						+ rs.getString("last_name");
				message.receiver = rs.getString("receiver");
				message.status = rs.getString("status");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	public ArrayList<MessageBean> selectSentMessageOf(String userName) {
		// TODO Auto-generated method stub
		ArrayList<MessageBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = con
					.prepareStatement("SELECT * FROM tbl_messages, tbl_users, tbl_reply_messages WHERE receiver = ? AND sender = tbl_users.user_name AND message_no = replied_to_msg_no");
			pstmt.setString(1, userName);
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

	public MessageBean selectSentMessageWith(int msgNo) {
		// TODO Auto-generated method stub
		MessageBean message = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = con
					.prepareStatement("SELECT * FROM tbl_reply_messages, tbl_messages, tbl_users WHERE message_no = ? AND replied_to_msg_no = message_no AND sender = tbl_users.user_name");
			pstmt.setInt(1, msgNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				message = new MessageBean();
				message.msgNo = rs.getInt("message_no");
				message.content = rs.getString("tbl_reply_messages.content");
				message.sentDate = rs
						.getTimestamp("tbl_reply_messages.sent_date");
				message.sender = rs.getString("sender");
				message.name = rs.getString("first_name") + " "
						+ rs.getString("last_name");
				message.receiver = rs.getString("receiver");
				message.status = rs.getString("status");
				System.out.println("msgNo in dao after sql = " + message.msgNo);
				System.out.println(message.name);
				System.out.println(message.sender);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	public boolean updateStatusToReply(int msgNo) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		boolean isReplied = false;
		String sql = "UPDATE tbl_messages SET status = ? WHERE message_no = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "replied");
			pstmt.setInt(2, msgNo);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				isReplied = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isReplied;
	}

	public boolean insertRepliedMessage(MessageBean message) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		boolean isInserted = false;
		String sql = "INSERT INTO tbl_reply_messages(sent_date, content, replied_to_msg_no) VALUES (?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setTimestamp(1, message.sentDate);
			pstmt.setString(2, message.content);
			pstmt.setInt(3, message.msgNo);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				isInserted = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isInserted;
	}

	public MessageBean selectRepliedMessage(int msgNo) {
		// TODO Auto-generated method stub
		MessageBean message = null;
		PreparedStatement pstmt;
		ResultSet rs;

		try {
			pstmt = con
					.prepareStatement("SELECT * FROM tbl_reply_messages, tbl_users WHERE replied_to_msg_no = ?");
			pstmt.setInt(1, msgNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				message = new MessageBean();
				message.msgNo = rs.getInt("replied_to_msg_no");
				message.content = rs.getString("content");
				message.sentDate = rs.getTimestamp("sent_date");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	public ArrayList<TimeTableBean> selectTimetableOf(String userName) {
		// TODO Auto-generated method stub
		ArrayList<TimeTableBean> result = null;
		PreparedStatement pstmt;
		ResultSet rs;
		String sql = "SELECT * FROM tbl_time_table WHERE lecturer_user_name =? ";

		try {
			pstmt = con.prepareStatement(sql);
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

	public boolean selectTimetableWith(TimeTableBean info) {
		// TODO Auto-generated method stub
		boolean result = false;
		PreparedStatement pstmt;
		ResultSet rs;
		String sql = "SELECT * FROM tbl_time_table WHERE lecturer_user_name =? AND time = ? AND day = ? ";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, info.userName);
			pstmt.setTime(2, info.time);
			pstmt.setInt(3, info.day);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean updateTimetableWith(TimeTableBean bean) {
		// TODO Auto-generated method stub
		boolean result = false;
		PreparedStatement pstmt;
		String sql = "UPDATE tbl_time_table SET subject = ? WHERE lecturer_user_name =? AND time = ? AND day = ? ";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.subject);
			pstmt.setString(2, bean.userName);
			pstmt.setTime(3, bean.time);
			pstmt.setInt(4, bean.day);
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

	public boolean insertTimetableWith(TimeTableBean bean) {
		// TODO Auto-generated method stub

		PreparedStatement pstmt = null;
		boolean result = false;

		String sql = "INSERT INTO tbl_time_table(subject, time, day, lecturer_user_name) VALUES (?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, bean.subject);
			pstmt.setTime(2, bean.time);
			pstmt.setInt(3, bean.day);
			pstmt.setString(4, bean.userName);

			int count = pstmt.executeUpdate();

			if (count > 0) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public boolean deleteTimetable(int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM tbl_time_table WHERE id = ?";
		int count = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			count = pstmt.executeUpdate();
			if (count > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
