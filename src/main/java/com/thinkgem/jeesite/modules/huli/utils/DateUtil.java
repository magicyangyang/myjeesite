package com.thinkgem.jeesite.modules.huli.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 */
public class DateUtil {
	public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	public static final String Y_M_D_H_M = "yyyy-MM-dd HH:mm";
	public static final String Y_M_D = "yyyy-MM-dd";

 
	public static void main(String[] args) {
		long s = diffDay(parseDate("2018-08-09", "yyyy-MM-dd HH:mm:ss"),new Date());
		System.out.println(s);
	}

	public static long getTime(Date date) {
		return date == null ? 0 : date.getTime();
	}

	public static String getDateStr(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}

	public static Date parseDate(String date, String... patterns) {
		try {
			return DateUtils.parseDate(date, patterns);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date parseDateFromTimestamp(Long times) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String d = format.format(times);
			return format.parse(d);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String parseDayStrFromTimestamp(Long times) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(times);
		} catch (Exception e) {
			return null;
		}
	}

	public static String parseDateStrFromTimestamp(Long times) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return format.format(times);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 计算两个日期间的相差天数，自然天
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long diffDay(Date d1, Date d2) {
		d1 = DateUtils.truncate(d1, Calendar.DAY_OF_MONTH);
		d2 = DateUtils.truncate(d2, Calendar.DAY_OF_MONTH);

		return Math.abs((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	/**
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isBeforeThanDay(Date d1, Date d2) {
		return d2.getTime() - d1.getTime()>0l;
	}

	public static Date getTodayDate() {
		Calendar cal = getCalendarToday();
		return cal.getTime();
	}

	private static Calendar getCalendarToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Date getTomorrowDate() {
		Calendar cal = getCalendarToday();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public static Date getNowAftre5m() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 5);
		return cal.getTime();
	}

	public static Date getNowAftre1Day() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		return cal.getTime();
	}

	public static Date getNowAftreXDay(int x) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + x);
		return cal.getTime();
	}

	/**
	 * 获取当前离第二天0点0分剩余毫秒数
	 * 
	 * @return
	 */
	public static long getTodayLastTime() {
		long nowTime = Calendar.getInstance().getTimeInMillis();
		return getTomorrowDate().getTime() - nowTime;
	}

	public static long dateConvertToLong(Date date) {
		return date == null ? 0 : date.getTime();
	}

	public static String format(Calendar calendar, String pattern) {
		return new SimpleDateFormat(pattern).format(calendar.getTime());
	}
 
	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDaysByMillisecond(Date date1, Date date2) {
		return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
	}

	/**
	 * date2比date1多的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		// 同一年
		if (year1 != year2) {
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				// 闰年
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
					timeDistance += 366;
				} else {
					timeDistance += 365;
				}
			}
			return timeDistance + (day2 - day1);
		} else // 不同年
		{
			System.out.println("判断day2 - day1 : " + (day2 - day1));
			return day2 - day1;
		}
	}
}
