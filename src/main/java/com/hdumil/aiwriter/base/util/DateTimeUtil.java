package com.hdumil.aiwriter.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

    //返回年月日 时分秒
	public static String getSysTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date();
		String dateStr = sdf.format(date);
		
		return dateStr;
		
	}

    //返回年月日 时分秒
    public static String getSysTime(long timeInMillis){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date(timeInMillis);
        String dateStr = sdf.format(date);

        return dateStr;

    }

    //返回年月日
    public static String getDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        String dateStr = sdf.format(date);

        return dateStr;
    }

    //返回年月日
    public static String getDate(long timeInMillis){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date(timeInMillis);
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     * 将指定的日期字符串转换成日期
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return 日期对象
     */
    public static Date parseDate(String dateStr, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            throw  new RuntimeException("日期转化错误");
        }

        return date;
    }

    /**
     * 将2022-02-04T14:03:50.261+08:00的日期字符串转换日期
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return 日期对象
     */
    public static Date parseDate_1(String dateStr, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            throw  new RuntimeException("日期转化错误");
        }

        return date;
    }

    /**
     * 将指定的日期格式化成指定的日期字符串
     * @param date 日期对象
     * @param pattern 格式
     * @return 格式化后的日期字符串
     */
    public static String dateFormate(Date date, String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr;
        if(date == null)
        {
            return "";
        }
        dateStr = sdf.format(date);
        return dateStr;
    }

    //返回两个时间相差的天数
    public static long testBetweenDays(String dateStr1, String dateStr2){
        // 获取日期
        Date date1 = parseDate(dateStr1, "yyyy-MM-dd HH:mm:ss");
        Date date2 = parseDate(dateStr2, "yyyy-MM-dd HH:mm:ss");

        // 获取相差的天数
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        long timeInMillis1 = calendar.getTimeInMillis();
        calendar.setTime(date2);
        long timeInMillis2 = calendar.getTimeInMillis();

        long betweenDays =  (timeInMillis2 - timeInMillis1) / (1000L*3600L*24L);

        return betweenDays;

    }

    public static long testBetweenMillis(Date date1, Date date2){
        return date2.getTime() - date1.getTime();
    }
}
