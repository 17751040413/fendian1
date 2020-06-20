package com.wowoniu.fendian.utils;


import java.util.Date;

/**
 * @author ZZT
 * 时间计算
 */
public class DateCal {

    /**
     * 获得相差秒
     * @param end
     * @param begin
     * @return
     */
    public  static long second(Date end, Date begin){

        return (end.getTime()-begin.getTime())/1000;
    }

    /**
     * 获得相差分
     * @param end
     * @param begin
     * @return
     */
    public static long minutie(Date end,Date begin){

        return second(end,begin)/60;
    }

    /**
     * 获得相差小时
     * @param end
     * @param begin
     * @return
     */
    public static long hour(Date end,Date begin){

        return minutie(end,begin)/60;
    }

    /**
     * 获得相差天
     * @param end
     * @param begin
     * @return
     */
    public static long day(Date end,Date begin){

        return hour(end,begin)/24;
    }

}
