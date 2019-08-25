package com.michael.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * 
 * @author guixin
 *
 */
public class DateUtil {

	/**
	 * 将日期转换为字符串
	 * 
	 * @return
	 */
	public static String dateFormat() {
		return dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String dateFormat(String pattern) {
		return dateFormat(new Date(), pattern);
	}

	public static String dateFormat(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param source
	 * @return
	 * @throws ParseException
	 */
	public static Date StrToDate(String source) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(source);
	}

	public static Date StrToDate(String source, String pattern)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(source);
	}

	/**
	 * 增加月
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date addMonths(Date date, int i) {
		return org.apache.commons.lang.time.DateUtils.addMonths(date, i);
	}

	/**
	 * 增加小时
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addHours(Date date, int amount) {
		return org.apache.commons.lang.time.DateUtils.addHours(date, amount);
	}

	/**
	 * 增加天数
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDays(Date date, int amount) {
		return org.apache.commons.lang.time.DateUtils.addDays(date, amount);
	}

	/**
	 * 日期格式换行为GMT格式
	 * 
	 * @param date
	 * @return
	 */
	public static String toGMTString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z",
				Locale.UK);
		df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
		return df.format(date);
	}

	/**
	 * 获取产品失效时间 1：包月 2：包季，3：包年 4:24小时单点 5：48小时单点
	 * 
	 * @param pay_type
	 * @time 使用时长
	 * @return
	 */
	public static Date getFailureTime(Date date, int pay_type, int time) {
		switch (pay_type) {
		case 1:
			date = addMonths(date, time);
			break;
		case 2:
			date = addMonths(date, time);
			break;
		case 3:
			date = addMonths(date, time);
			break;
		case 4:
			date = addHours(date, time);
			break;
		case 5:
			date = addHours(date, time);
			break;
		default:
			break;
		}
		return date;
	}

	/**
	 * 获取传入日期和当前当前日期相隔天数
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getDateDiffDays(String date) throws ParseException {
		long now = new Date().getTime();
		long ago = StrToDate(date, "yyyy-MM-dd").getTime();

		long intervalMilli = now - ago;
		if (intervalMilli < 0) {
			return 0;
		}

		return (int) (intervalMilli / (24 * 3600 * 1000));
	}

}
