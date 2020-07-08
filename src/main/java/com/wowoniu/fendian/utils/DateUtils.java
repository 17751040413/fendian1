package com.wowoniu.fendian.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期处理工具类
 *
 * @author yuany
 * @date 2020-05-11
 */
public class DateUtils {

    private final static SimpleDateFormat SDF_YEAR = new SimpleDateFormat("yyyy");

    private final static SimpleDateFormat SDF_YEAR_MONTH = new SimpleDateFormat("yyyy-MM");

    private final static SimpleDateFormat SDF_DAY = new SimpleDateFormat(
            "yyyy-MM-dd");

    private final static SimpleDateFormat SDF_DAYS = new SimpleDateFormat(
            "yyyyMMdd");

    private final static SimpleDateFormat SDF_TIME = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String formatTime(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 将系统时间转成毫秒数
     */
    public static Long dateToSs() {
        long time2 = System.currentTimeMillis();
        return time2;
    }

    /**
     * 将毫秒数转成日期时间格式
     */
    public static String ssTodate(long time3) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = new Date();
        date2.setTime(time3);
        System.out.println(simpleDateFormat.format(date2));
        return simpleDateFormat.format(date2);
    }

    //通用时间格式化工具:格式由用户自己自由指定,通用性比较高
    //private final static SimpleDateFormat sdf = new SimpleDateFormat();

    /**
     * 获取YYYY格式
     */
    public static String getYear() {
        return SDF_YEAR.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay() {
        return SDF_DAY.format(new Date());
    }

    /**
     * 获取YYYY-MM格式
     */
    public static String getMonth() {
        return SDF_YEAR_MONTH.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays() {
        return SDF_DAYS.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime() {
        return SDF_TIME.format(new Date());
    }


    /**
     * string类型转换为long类型
     */
    public static long stringToLong(String strTime, String formatType) {
        // String类型转成date类型
        Date date = stringToDate(strTime, formatType);
        if (date == null) {
            return 0;
        } else {
            // date类型转成long类型
            long currentTime = dateToLong(date);
            return currentTime;
        }
    }

    /**
     * date类型转换为long类型
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * long类型转换为date类型
     */
    public static String longToString(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt = new Date(time);
        return sdf.format(dt);
    }

    /**
     * long类型转换为date类型
     */
    public static String longToStringParams(Long time, String params) {
        SimpleDateFormat sdf = new SimpleDateFormat(params);
        //前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt = new Date(time);
        return sdf.format(dt);
    }

    /**
     * 得到n天之后的日期
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);
        // java.util包
        Calendar canlendar = Calendar.getInstance();
        // 日期减 如果不够减会将月变动
        canlendar.add(Calendar.DATE, daysInt);
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 获取指定日期的前一天的时间
     */
    public static String preTime(String time) {
        Calendar c = Calendar.getInstance();
        Date data = null;
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(data);
        int preDay = c.get(Calendar.DATE);
        c.set(Calendar.DATE, preDay - 1);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 将时间格式转换成时间戳
     */
    public static Long dateToStamps(String date) {
        Long res = 0L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date da = sdf.parse(date);
            res = da.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取当月第一天
     */
    public static String getMonthFirstDay() {
        // 获取前月的第一天an's
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, -1);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return SDF_DAY.format(cale.getTime());
    }

    /**
     * 获取当月最后一天
     */
    public static String getMonthLastDay() {
        // 获取前月的第一天
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return SDF_DAY.format(cale.getTime());
    }

    /**
     * +1秒
     */
    public static Date getNextSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, +1);
        date = c.getTime();
        return date;
    }

    /**
     * 获取N秒之前的时间
     */
    public static String getBeforeSecondTime(int s) {
        //30分钟
        long time = s * 1000;
        Date beforeDate = new Date(System.currentTimeMillis() - time);
        return SDF_TIME.format(beforeDate);
    }

    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(Long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将“Thu May 18 2017 00:00:00 GMT+0800 (中国标准时间)” 转 "yy-MM-dd"
     **/
    public static String parseTime(String datdString) {
        datdString = datdString.replace("GMT", "").replaceAll("\\(.*\\)", "");
        //将字符串转化为date类型，格式2016-10-12
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        Date dateTrans = null;
        try {
            dateTrans = format.parse(datdString);
            return new SimpleDateFormat("yyyy-MM-dd").format(dateTrans);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datdString;

    }

    /**
     * 获得相差秒
     *
     * @param end
     * @param begin
     * @return
     */
    public static long second(Date end, Date begin) {

        return (end.getTime() - begin.getTime()) / 1000;
    }

    /**
     * 获得相差分
     *
     * @param end
     * @param begin
     * @return
     */
    public static long minutie(Date end, Date begin) {

        return second(end, begin) / 60;
    }

    /**
     * 获得相差小时
     *
     * @param end
     * @param begin
     * @return
     */
    public static long hour(Date end, Date begin) {

        return minutie(end, begin) / 60;
    }

    /**
     * 获得相差天
     *
     * @param end
     * @param begin
     * @return
     */
    public static long day(Date end, Date begin) {

        return hour(end, begin) / 24;
    }



}