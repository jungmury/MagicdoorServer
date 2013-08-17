package jy.kim.lancs.magicdoor.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import jy.kim.lancs.magicdoor.rest.util.JsonUtil;

public class TimeUtilForMagicdoor {

	private static JsonUtil instance = null;
	private static final int MON_VAL = 2;

	static JsonUtil getInstance() {
		if (instance == null) {
			instance = new JsonUtil();
		}
		return instance;
	}

	public TimeUtilForMagicdoor() {
		// Exists only to defeat instantiation.
	}

	public static Timestamp getTodayTimestamp() {
		Timestamp writtenDate = null;
		Date date = new Date();
		long time = date.getTime();
		writtenDate = new Timestamp(time);

		return writtenDate;
	}

	public static int getDayValue(String val) {
		int day = 0;
		if (val.equals("Mon")) {
			day = MON_VAL;
		} else if (val.equals("Tue")) {
			day = MON_VAL + 1;
		} else if (val.equals("Wed")) {
			day = MON_VAL + 2;
		} else if (val.equals("Thu")) {
			day = MON_VAL + 3;
		} else if (val.equals("Fri")) {
			day = MON_VAL + 4;
		}
		return day;
	}

	public static Time getHourValue(String val) {
		// TODO Auto-generated method stub
		String[] h = val.split(":");
		int hour = Integer.parseInt(h[0]);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Time time = new Time(cal.getTimeInMillis());
		return time;
	}
}
