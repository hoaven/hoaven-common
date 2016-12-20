package com.hoaven.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * 获取月份字符串
	 *
	 * @param f
	 * @param format
	 * @return
	 */
	public static String getMonth(int f, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, f);
		DateFormat df = new SimpleDateFormat(format);
		String result = df.format(calendar.getTime());
		return result;
	}

	public static String getDay(int f, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, f);
		DateFormat df = new SimpleDateFormat(format);
		String result = df.format(calendar.getTime());
		return result;
	}

	public static String getDay(int f, String format, String dateStr) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, f);

		String result = df.format(calendar.getTime());
		return result;
	}

	public static String format(Date date, String format) {
		if (date == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static String convertDate(Date date) {
		return date == null ? "" : DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 */
	public static String formatForTime(Date date) {
		if (date == null) {
			return "";
		}
		String format = "yyyy-MM-dd HH:mm:ss";
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 当前年月日
	 *
	 * @return
	 */
	public static String getCurrDateStr8() {
		return format(new Date(), "yyyyMMdd");
	}

	/**
	 * 当前年月日时分
	 *
	 * @return
	 */
	public static String getCurrDateStr12() {
		return format(new Date(), "yyyyMMddHHmm");
	}

	/**
	 * 当前年月日时分
	 *
	 * @return
	 */
	public static String getCurrDateStr14() {
		return format(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 当前时分秒毫秒
	 *
	 * @return
	 */
	public static String getCurrTimeStr8() {
		String str = format(new Date(), "HHmmssSSS");
		if (str.length() > 8) {
			str = str.substring(0, 8);
		}
		return str;
	}

	public static boolean compareWithNow(Date date, int minute) {
		// long difTime = 1000 * 60 * minute;
		Date now = new Date();
		long nowTime = now.getTime();
		long otherTime = date.getTime();
		// boolean result = nowTime - otherTime > difTime;

		/******
		 * 2015/12/23 delu 目前临时单最长有效期为两天
		 **************************************************/

		long l = nowTime - otherTime;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long totalMin = day * 24 * 60 + min;

		if (totalMin > minute) {
			return true;
		} else {
			return false;
		}
	}

	public static long getMillSeconds(Date oldDate, Date newDate) {

		long nowTime = newDate.getTime();
		long oldTime = oldDate.getTime();
		return nowTime - oldTime;

	}

	public static Date toDate(String str, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		return df.parse(str);
	}

	public static void main(String[] args) {
		System.out.println(getMonth(1, "yyyyMM"));
	}

	public static Date getStartWithCurrentDay(Date currentTime) {
		Calendar calendar = Calendar.getInstance();
		currentTime.setSeconds(0);
		currentTime.setMinutes(0);
		currentTime.setHours(0);
		calendar.setTime(currentTime);
		return calendar.getTime();
	}

	public static Date getEndWithCurrentDay(Date currentTime) {
		Calendar calendar = Calendar.getInstance();
		currentTime.setSeconds(59);
		currentTime.setMinutes(59);
		currentTime.setHours(23);
		calendar.setTime(currentTime);
		return calendar.getTime();
	}

	/**
	 * 获得日期数组
	 *
	 * @param calendarType
	 *            日期跨度的类型，
	 */

	public static Date[] getDayArrays(Date start, Date end, int calendarType) {
		ArrayList<Date> ret = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		Date tmpDate = calendar.getTime();
		long endTime = end.getTime();
		while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
			ret.add(calendar.getTime());
			calendar.add(calendarType, 1);
			tmpDate = calendar.getTime();
		}

		Date[] dates = new Date[ret.size()];
		return ret.toArray(dates);
	}

	/**
	 * 获得日期字符串数组
	 *
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public static String[] getDayArrays(String start, String end) throws ParseException {
		Date dateStart = DateUtils.toDate(start, "yyyy-MM-dd");
		Date dateEnd = DateUtils.toDate(end, "yyyy-MM-dd");
		Date[] strArray = getDayArrays(dateStart, dateEnd, Calendar.DAY_OF_YEAR);
		String[] retArray = new String[strArray.length];
		int index = 0;
		for (Date string : strArray) {
			retArray[index] = DateUtils.format(string, "yyyy-MM-dd");
			System.out.println(DateUtils.format(string, "yyyy-MM-dd"));
			index++;
		}
		return retArray;
	}

	private static final char Splitter_Minus = '-';
	private static final char Splitter_Slash = '/';
	private static final SimpleDateFormat FORMAT_SLASH_SHORT = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private static final SimpleDateFormat FORMAT_SLASH_FULL = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static final SimpleDateFormat FORMAT_SLASH_DATE = new SimpleDateFormat("yyyy/MM/dd");
	private static final SimpleDateFormat FORMAT_SLASH_TIME = new SimpleDateFormat("HH:mm:ss");

	private static final SimpleDateFormat FORMAT_SLASH_SHORT_MINUS_SIGN = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final SimpleDateFormat FORMAT_SLASH_FULL_MINUS_SIGN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat FORMAT_SLASH_DATE_MINUS_SIGN = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 如果参数长度不为为0,则取第一个参数进行格式化，<br>
	 * 否则取当前日期时间，精确到秒 如:2010-4-15 9:36:38
	 * <p>
	 * ... 可变参数
	 *
	 * @return java.lang.String *
	 */
	public static String toFull(Date... date) {
		SimpleDateFormat simple = FORMAT_SLASH_FULL;
		if (date.length > 0) {
			return simple.format(date[0]);
		}
		return simple.format(new Date());
	}

	/**
	 * 如果参数长度不为为0,则取第一个参数进行格式化，<br>
	 * 否则取当前日期 如:2010-4-15
	 * <p>
	 * ... 可变参数
	 *
	 * @return java.lang.String *
	 */
	public static String toDate(Date... date) {
		SimpleDateFormat simple = FORMAT_SLASH_DATE;
		if (date.length > 0) {
			return simple.format(date[0]);
		}
		return simple.format(new Date());
	}

	/**
	 * 如果参数长度不为为0,则取第一个参数进行格式化，<br>
	 * 否则取当前日期时间，精确到秒<br>
	 * 如:9:36:38
	 * <p>
	 * ... 可变参数
	 *
	 * @return java.lang.String *
	 */
	public static String toTime(Date... date) {
		SimpleDateFormat simple = FORMAT_SLASH_TIME;
		if (date.length > 0) {
			return simple.format(date[0]);
		}
		return simple.format(new Date());
	}

	/**
	 * 根据字符串格式去转换相应格式的日期和时间
	 *
	 * @return java.util.Date
	 * @throws ParseException
	 *             如果参数格式不正确会抛出此异常 *
	 */
	public static Date reverse2Date(String date) {
		SimpleDateFormat simple = null, simpleStandby = null;
		date = date.trim();
		switch (date.length()) {
		case 19:// 日期+时间
			simple = FORMAT_SLASH_FULL;
			simpleStandby = FORMAT_SLASH_FULL_MINUS_SIGN;
			break;
		case 16:// 日期+时间（到分钟）
			simple = FORMAT_SLASH_SHORT;
			simpleStandby = FORMAT_SLASH_SHORT_MINUS_SIGN;
			break;
		case 10:// 仅日期
			simple = FORMAT_SLASH_DATE;
			simpleStandby = FORMAT_SLASH_DATE_MINUS_SIGN;
			break;
		case 8:// 仅时间
			simple = FORMAT_SLASH_TIME;
			simpleStandby = FORMAT_SLASH_TIME;
			break;
		default:
			break;
		}
		try {
			return simple.parse(date);
		} catch (ParseException e) {
			try {
				return simpleStandby.parse(date);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将带有时、分、秒格式的日期转化为00:00:00<br>
	 * 方便日期推算,格式化后的是yyyy-MM-dd 00:00:00
	 *
	 * @return java.util.Date *
	 */
	public static Date startOfADay(Date... date) {
		SimpleDateFormat simple = FORMAT_SLASH_DATE;
		Date date_ = date.length == 0 ? new Date() : date[0];// 如果date为null则取当前时间
		String d = simple.format(date_);
		try {
			return simple.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 推算一个月内向前或向后偏移多少天,当然推算年也可以使用
	 *
	 * @param date
	 *            要被偏移的日期,<br>
	 *            amout 偏移量<br>
	 *            b 是否先将date格式化为yyyy-MM-dd 00:00:00 即: 是否严格按整天计算
	 * @return java.util.Date *
	 */
	public static Date addDayOfMonth(Date date, Integer amount, Boolean b) {
		date = date == null ? new Date() : date;// 如果date为null则取当前日期
		if (b) {
			date = startOfADay(date);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
	}

	/**
	 * 推算一个小时内向前或向后偏移多少分钟,除了秒、毫秒不可以以外,其他都可以
	 *
	 * @param date
	 *            要被偏移的日期,<br>
	 *            amout 偏移量<br>
	 *            b 是否先将date格式化为yyyy-MM-dd HH:mm:00 即: 是否严格按整分钟计算
	 * @return java.util.Date *
	 */
	public static Date addMinuteOfHour(Date date, Integer amount, Boolean b) {
		date = date == null ? new Date() : date;// 如果date为null则取当前日期
		if (b) {
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
			try {
				date = simple.parse(simple.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, amount);
		return cal.getTime();
	}

	/**
	 * 推算一个月内向前或向后偏移多少天,当然推算年也可以使用
	 *
	 * @param date
	 *            要被偏移的日期,<br>
	 *            amout 偏移量<br>
	 *            b 是否先将date格式化为yyyy-MM-dd 00:00:00 即: 是否严格按整天计算
	 * @return java.util.Date *
	 */
	public static Date addDay(Date date, Integer amount) {
		date = date == null ? new Date() : date;// 如果date为null则取当前日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, amount);
		return cal.getTime();
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 *
	 * @return String
	 */
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * 根据年份和月份返回当月的天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDayNumsByMonth(int year, int month) {

		boolean flag = false;
		// 判断是否是闰年
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			flag = true;
		}

		if (month == 2) {
			if (flag) {
				return 29;
			} else {
				return 28;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else {
			return 31;
		}
	}

	/**
	 * 获取日期的年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 计算两个日期之间的天数
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(Date begindate, Date enddate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		begindate = sdf.parse(sdf.format(begindate));
		enddate = sdf.parse(sdf.format(enddate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(begindate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(enddate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 是否是最后一天
	 */
	public static boolean isLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DATE) == cal.getActualMaximum(Calendar.DAY_OF_MONTH))
			return true;
		else
			return false;
	}

	public static boolean isFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day == 1) {
			return true;
		} else {
			return false;
		}
	}
}
