package jy.kim.lancs.magicdoor.rest.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import jy.kim.lancs.magicdoor.rest.bean.AnnouncementReqBean;
import jy.kim.lancs.magicdoor.rest.bean.AppointmentReqDetailBean;
import jy.kim.lancs.magicdoor.rest.bean.LogInInfoReqBean;
import jy.kim.lancs.magicdoor.rest.bean.MessageBean;
import jy.kim.lancs.magicdoor.rest.bean.NFCTagInfoBean;
import jy.kim.lancs.magicdoor.rest.bean.QueryForAppointments;
import jy.kim.lancs.magicdoor.rest.bean.TimeTableBean;
import jy.kim.lancs.magicdoor.rest.bean.UserAccountInfoBean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtil {
	private static JsonUtil instance = null;

	static JsonUtil getInstance() {
		if (instance == null) {
			instance = new JsonUtil();
		}
		return instance;
	}

	public JsonUtil() {
		// Exists only to defeat instantiation.
	}

	// parsing json using json-simple parsers
	public static LogInInfoReqBean parsingUserNameAndPwd(String message) {
		// TODO Auto-generated method stub
		LogInInfoReqBean params = null;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(message);
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
				params = new LogInInfoReqBean();
				params.setUserName((String) jsonObject.get("userName"));
				params.setPassword((String) jsonObject.get("password"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return params;
	}

	public static UserAccountInfoBean parsingSignUpInfo(String jsonValue) {
		// TODO Auto-generated method stub
		UserAccountInfoBean params = null;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(jsonValue);
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
				params = new UserAccountInfoBean();
				params.setUserName((String) jsonObject.get("userName"));
				params.setPassword((String) jsonObject.get("password"));
				params.setFirstName((String) jsonObject.get("firstName"));
				params.setLastName((String) jsonObject.get("lastName"));
				params.seteMailAddress((String) jsonObject.get("eMailAddress"));
				params.setUserType((String) jsonObject.get("userType"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return params;
	}

	public static AnnouncementReqBean parsingNewAnnouncement(String jsonValue) {
		// TODO Auto-generated method stub
		AnnouncementReqBean params = null;
		JSONParser parser = new JSONParser();
		try {

			Object obj = parser.parse(jsonValue);
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
				params = new AnnouncementReqBean();
				params.setUserName((String) jsonObject.get("userName"));
				params.setAnnouncementContent((String) jsonObject
						.get("content"));
				params.setTitle((String) jsonObject.get("title"));
				params.setWrittenDate(Timestamp
						.valueOf(convertSeparators((String) jsonObject
								.get("writtenDate"))));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return params;
	}

	private static String convertSeparators(String input) {
		char[] chars = input.toCharArray();
		chars[10] = ' ';
		chars[13] = ':';
		chars[16] = ':';
		return new String(chars);
	}

	public static AppointmentReqDetailBean parsingAppointmentRequest(
			String appInfo) {
		// TODO Auto-generated method stub
		AppointmentReqDetailBean params = null;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(appInfo);
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
				params = new AppointmentReqDetailBean();
				params.refValue = (String) jsonObject.get("refValue");
				params.title = (String) jsonObject.get("title");
				params.lecturerUserName = (String) jsonObject
						.get("lecturerUserName");
				params.requesterUserName = (String) jsonObject
						.get("requesterUserName");
				params.description = (String) jsonObject.get("description");
				String sDate = (String) jsonObject.get("bookingDate");
				String sTime = (String) jsonObject.get("bookingTime");
				DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd",
						Locale.UK);
				try {
					params.bookingDate = new Date(dFormat.parse(sDate)
							.getTime());
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				params.bookingTime = Time.valueOf(sTime);
				params.status = (String) jsonObject.get("status");
				params.duration = ((Long) jsonObject.get("duration"))
						.intValue();
				System.out.println("params sDate = " + sDate);
				System.out.println("params params.bookingDate = "
						+ params.bookingDate);

				System.out.println("params refValue = " + params.refValue);
				System.out.println("params title = " + params.title);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return params;
	}

	public static QueryForAppointments parsingQueryForAppointmentsWithDateOnly(
			String query) {
		// TODO Auto-generated method stub
		QueryForAppointments params = null;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(query);
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
				params = new QueryForAppointments();
				params.requesterUserName = ((String) jsonObject
						.get("requesterUserName"));
				params.tagOwner = ((String) jsonObject.get("tagOwner"));
				String sDate = (String) jsonObject.get("date");
				DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd",
						Locale.UK);
				try {
					params.date = new Date(dFormat.parse(sDate).getTime());
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return params;
	}

	public static QueryForAppointments parsingQueryForAppointments(String query) {
		// TODO Auto-generated method stub
		QueryForAppointments params = null;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(query);
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
				params = new QueryForAppointments();
				params.status = (String) jsonObject.get("status");
				params.note = (String) jsonObject.get("note");
				params.refValue = (String) jsonObject.get("refValue");
				params.requesterUserName = ((String) jsonObject
						.get("requesterUserName"));
				params.tagOwner = ((String) jsonObject.get("tagOwner"));
				String sDate = (String) jsonObject.get("date");
				DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd",
						Locale.UK);
				try {
					if (sDate != null) {
						params.date = new Date(dFormat.parse(sDate).getTime());
					}
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return params;
	}

	public static TimeTableBean parsingTimeTableInfo(String string) {
		// TODO Auto-generated method stub

		TimeTableBean params = null;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(string);
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
				params = new TimeTableBean();
				params.id = Integer.parseInt(String.valueOf(jsonObject.get("id")));
				params.day = Integer.parseInt(String.valueOf(jsonObject
						.get("day")));
				params.subject = (String) jsonObject.get("subject");
				params.userName = (String) jsonObject.get("userName");
				String sTime = (String) jsonObject.get("time");
				DateFormat dFormat = new SimpleDateFormat("HH:mm:ss", Locale.UK);
				try {
					params.time = new Time(dFormat.parse(sTime).getTime());
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return params;
	}

	public static MessageBean parsingNewMessage(String string) {
		// TODO Auto-generated method stub
		MessageBean params = null;
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(string);
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
				params = new MessageBean();
				params.msgNo = Integer.parseInt(String.valueOf(jsonObject
						.get("msgNo")));
				params.content = (String) jsonObject.get("content");
				params.receiver = (String) jsonObject.get("receiver");
				params.sender = (String) jsonObject.get("sender");
				params.sentDate = new Timestamp(
						(Long) jsonObject.get("sentDate"));
				params.status = (String) jsonObject.get("status");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return params;
	}

	public static NFCTagInfoBean parsingNewTag(String string) {
		// TODO Auto-generated method stub
		NFCTagInfoBean params = null;
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(string);
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
				params = new NFCTagInfoBean();
				params.tagId = (String) jsonObject.get("tagId");
				params.tagOwner = (String) jsonObject.get("tagOwner");
				params.serviceDescription = (String) jsonObject
						.get("serviceDescription");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return params;
	}
}
