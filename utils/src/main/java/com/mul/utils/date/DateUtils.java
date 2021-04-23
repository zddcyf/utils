package com.mul.utils.date;

import android.text.TextUtils;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @ProjectName: youlinanzhuoyuanshengduan
 * @Package: com.wisdom.tdweilaiapp.util
 * @ClassName: DateUtils
 * @Author: zdd
 * @CreateDate: 2019/12/18 8:41
 * @Description: 时间转换器工具
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/12/18 8:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public enum DateUtils {
    INSTANCE;
    private final String format = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期字符串转换Date实体
     *
     * @param serverTime 时间
     * @return 返回日期
     */
    public Date parseServerTime(String serverTime) {
        return parseServerTime(serverTime, format);
    }

    /**
     * 日期字符串转换Date实体
     *
     * @param serverTime 时间
     * @return 返回日期
     */
    public Date parseServerTime(String serverTime, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(serverTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前时间:年月日时分秒
     */
    public String getDateAndTime() {
        return getDateAndTime(format);
    }

    /**
     * 获取当前时间:年月日时分秒
     */
    public String getDateAndTime(String format) {
        return (String) DateFormat.format(format, System.currentTimeMillis());
    }

    /**
     * 获取当前时间:年月日
     */
    public String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 获取当前时间:年月日
     */
    public String getDate(String format) {
        return (String) DateFormat.format(format, System.currentTimeMillis());
    }

    /**
     * 获取当前时间:年
     */
    public String getDateYear() {
        String format = (String) DateFormat.format("yyyy-MM-dd", System.currentTimeMillis());
        return format.split("-")[0];
    }

    /**
     * 获取当前时间:月
     */
    public String getDateMonth() {
        String format = (String) DateFormat.format("yyyy-MM-dd", System.currentTimeMillis());
        return format.split("-")[1];
    }

    /**
     * 获取当前时间:日
     */
    public String getDateDay() {
        String format = (String) DateFormat.format("yyyy-MM-dd", System.currentTimeMillis());
        return format.split("-")[2];
    }

    /**
     * 获取周几
     */
    public int getIntWeekOfDay() {
        return Integer.parseInt(getWeekOfDay(getDate("yyyy-MM-dd"), 1));
    }

    /**
     * 获取周几
     */
    public String getStrWeekOfDay() {
        return getWeekOfDay(getDate("yyyy-MM-dd"), 2);
    }

    /**
     * 获取周几
     *
     * @param date 获取固定时间的周几
     * @return 返回周几
     */
    public int getIntWeekOfDay(String date) {
        return Integer.parseInt(getWeekOfDay(date, 1));
    }

    /**
     * 获取周几
     *
     * @param date 获取固定时间的周几
     * @return 返回周几
     */
    public String getStrWeekOfDay(String date) {
        return getWeekOfDay(date, 2);
    }

    /**
     * 获取周几
     *
     * @param date 获取固定时间的周几
     * @return 返回周几
     */
    public String getWeekOfDay(String date, int type) {
        Calendar weekC = Calendar.getInstance();
        weekC.setTime(parseServerTime(date, "yyyy-MM-dd"));
        int week = weekC.get(Calendar.DAY_OF_WEEK);
        String dayOfWeek = "7";
        String dayOfWeekStr = "日";
        if (week == 1) {
            dayOfWeek = "7";
            dayOfWeekStr = "日";
        }
        if (week == 2) {
            dayOfWeek = "1";
            dayOfWeekStr = "一";
        }
        if (week == 3) {
            dayOfWeek = "2";
            dayOfWeekStr = "二";
        }
        if (week == 4) {
            dayOfWeek = "3";
            dayOfWeekStr = "三";
        }
        if (week == 5) {
            dayOfWeek = "4";
            dayOfWeekStr = "四";
        }
        if (week == 6) {
            dayOfWeek = "5";
            dayOfWeekStr = "五";
        }
        if (week == 7) {
            dayOfWeek = "6";
            dayOfWeekStr = "六";
        }
        return type == 1 ? dayOfWeek : dayOfWeekStr;
    }

    /**
     * 获取当前时间:年月日
     */
    public String getDate(long lSeconds) {
        return (String) DateFormat.format("yyyy-MM-dd", lSeconds);
    }

    /**
     * date转换成时间
     *
     * @param date 日期
     * @return 时间
     */
    public String getDate(Date date) {
        return getDate(date, format);
    }

    /**
     * date转换成时间
     *
     * @param date   时间
     * @param format 格式化模式
     * @return 返回格式化后的日期
     */
    public String getDate(Date date, String format) {
        SimpleDateFormat sdr = new SimpleDateFormat(format);
        return sdr.format(date);
    }

    /**
     * 获取当前时间:年
     */
    public String getDateYear(long lSeconds) {
        return (String) DateFormat.format("yyyy", lSeconds);
    }

    /**
     * 获取当前时间:月
     */
    public String getDateMonth(long lSeconds) {
        return (String) DateFormat.format("MM", lSeconds);
    }

    /**
     * 获取当前时间:日
     */
    public String getDateDay(long lSeconds) {
        return (String) DateFormat.format("dd", lSeconds);
    }

    /**
     * 秒数转换成时分秒
     *
     * @param lSeconds 时间戳
     * @return 返回时分秒
     */
    public String convertSecToTimeString(long lSeconds) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:mm:ss");
        return sdr.format(new Date(lSeconds));
    }

    /**
     * 秒数转换成时分
     *
     * @param lSeconds 实际戳
     * @return 时分
     */
    public String getHourToMin(long lSeconds) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:mm");
        return sdr.format(new Date(lSeconds));
    }

    /**
     * 秒数转换成时
     *
     * @param lSeconds 时间戳
     * @return 时
     */
    public String getHour(long lSeconds) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH");
        return sdr.format(new Date(lSeconds));
    }

    /**
     * 秒数转换成分
     *
     * @param lSeconds 时间戳
     * @return 分
     */
    public String getMin(long lSeconds) {
        SimpleDateFormat sdr = new SimpleDateFormat("mm");
        return sdr.format(new Date(lSeconds));
    }

    /**
     * 秒数转换成秒
     *
     * @param lSeconds 时间戳
     * @return 秒
     */
    public String getSec(long lSeconds) {
        SimpleDateFormat sdr = new SimpleDateFormat("ss");
        return sdr.format(new Date(lSeconds));
    }

    /**
     * Date对象获取时间字符串
     *
     * @param date 时间
     * @return 年月日时分秒
     */
    public String getDateStr(Date date) {
        return getDateStr(date, format);
    }

    /**
     * Date对象获取时间字符串
     *
     * @param date 时间
     * @return 返回自主设置的格式的时间
     */
    public String getDateStr(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 计算时间总共是多少小时
     *
     * @param lSeconds 时间戳
     * @return 时
     */
    public String convertHour(long lSeconds) {
        return String.format("%s", lSeconds / 1000 / 3600);
    }

    /**
     * 计算时间总共是多少分钟
     *
     * @param lSeconds 时间戳
     * @return 分
     */
    public String convertMin(long lSeconds) {
        return String.format("%s", lSeconds / 1000 / 60);
    }

    /**
     * 计算时间总共是多少秒
     *
     * @param lSeconds 时间戳
     * @return 秒
     */
    public String convertSec(long lSeconds) {
        return String.format("%s", lSeconds / 1000);
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @param date 时间
     * @return 返回时间戳
     */
    public long getSecondTimestampTwo(Date date) {
        if (null == date) {
            return 0;
        }
        return date.getTime();
    }

    /**
     * 时间戳转换日期格式字符串
     *
     * @param time 时间戳
     * @return 返回年月日时分秒
     */
    public String timeStamp2Date(long time) {
        return timeStamp2Date(time, format);
    }

    /**
     * 时间戳转换日期格式字符串
     *
     * @param time 时间戳
     * @return 返回用户设置格式的时间
     */
    public String timeStamp2Date(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    /**
     * 日期格式字符串转换时间戳 yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr 日期
     * @return 时间戳
     */
    public long date2TimeStamp(String dateStr) {
        return date2TimeStamp(dateStr, format);
    }

    /**
     * 日期格式字符串转换时间戳 yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr 日期
     * @return 时间戳
     */
    public long date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取某个日期前后N天的日期
     *
     * @param beginDate   固定的日期
     * @param distanceDay 前后几天 如获取前7天日期则传-7即可；如果后7天则传7  日期格式，默认"yyyy-MM-dd"
     * @return 日期
     */
    public String getOldDateByDay(Date beginDate, int distanceDay) {
        return getOldDateByDay(beginDate, distanceDay, format);
    }

    /**
     * 获取某个日期前后N天的日期
     *
     * @param beginDate   固定的日期
     * @param distanceDay 前后几天 如获取前7天日期则传-7即可；如果后7天则传7  日期格式，默认"yyyy-MM-dd"
     * @return 日期
     */
    public String getOldDateByDay(Date beginDate, int distanceDay, String format) {
        SimpleDateFormat dft = new SimpleDateFormat(format);
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 获取前后几个月的日期 日期格式，默认"yyyy-MM-dd"
     *
     * @param beginDate     固定的日期
     * @param distanceMonth 前后几天 如获取前7天日期则传-7即可；如果后7天则传7  日期格式，默认"yyyy-MM-dd"
     * @return 日期
     */
    public String getOldDateByMonth(Date beginDate, int distanceMonth) {
        return getOldDateByMonth(beginDate, distanceMonth, format);
    }

    /**
     * 获取前后几个月的日期 日期格式，默认"yyyy-MM-dd"
     *
     * @param beginDate     固定的日期
     * @param distanceMonth 前后几天 如获取前7天日期则传-7即可；如果后7天则传7  日期格式，默认"yyyy-MM-dd"
     * @return 日期
     */
    public String getOldDateByMonth(Date beginDate, int distanceMonth, String format) {
        SimpleDateFormat dft = new SimpleDateFormat(format);
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.MONTH, date.get(Calendar.MONTH) + distanceMonth);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay 日期
     * @return 前一天
     */
    public String getSpecifiedDayBefore(String specifiedDay) {
        return getSpecifiedDayBefore(specifiedDay, "yyyy-MM-dd");
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay 日期
     * @return 前一天
     */
    public String getSpecifiedDayBefore(String specifiedDay, String format) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        return new SimpleDateFormat(format).format(c.getTime());
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay 日期
     * @return 返回后一天
     */
    public String getSpecifiedDayAfter(String specifiedDay) {
        return getSpecifiedDayAfter(specifiedDay, "yyyy-MM-dd");
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay 日期
     * @return 返回后一天
     */
    public String getSpecifiedDayAfter(String specifiedDay, String format) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        return new SimpleDateFormat(format).format(c.getTime());
    }

    public String msgDate(long time) {
        if (getDate(time).equals(getDate())) {
            return getHourToMin(time);
        } else {
            // 目标年月日
            int aimsYear = Integer.parseInt(getDateYear(time));
            int aimsMouth = Integer.parseInt(getDateMonth(time));
            int aimsDay = Integer.parseInt(getDateDay(time));
            // 当前年月日
            int currentYear = Integer.parseInt(getDateYear());
            int currentMouth = Integer.parseInt(getDateMonth());
            int currentDay = Integer.parseInt(getDateDay());
            if (currentYear > aimsYear) {
                return getDate(time);
            } else if (currentMouth > aimsMouth) {
                return getDate(time);
            } else {
                if (currentDay - aimsDay == 1) {
                    return "昨天";
                } else {
                    return getDate(time);
                }
            }
        }
    }

    /**
     * 获取一周的日期
     *
     * @param time   时间
     * @param format 格式
     * @return 返回一周的日期列表
     */
    public List<String> convertWeekByDate(String time, String format) {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(format); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseServerTime(TextUtils.isEmpty(time) ? getDateAndTime(format) : time, format));
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得传入日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); //输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得传入日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给传入日期减去星期几与一个星期第一天的差值
        String Monday = sdf.format(cal.getTime());
        System.out.println("所在周星期一的日期：" + Monday);
        dates.add(Monday);
        cal.add(Calendar.DATE, 1);
        String Tuesday = sdf.format(cal.getTime());
        System.out.println("所在周星期二的日期：" + Tuesday);
        dates.add(Tuesday);
        cal.add(Calendar.DATE, 1);
        String Wednesday = sdf.format(cal.getTime());
        System.out.println("所在周星期三的日期：" + Wednesday);
        dates.add(Wednesday);
        cal.add(Calendar.DATE, 1);
        String Thursday = sdf.format(cal.getTime());
        System.out.println("所在周星期四的日期：" + Thursday);
        dates.add(Thursday);
        cal.add(Calendar.DATE, 1);
        String Friday = sdf.format(cal.getTime());
        System.out.println("所在周星期五的日期：" + Friday);
        dates.add(Friday);
        cal.add(Calendar.DATE, 1);
        String Saturday = sdf.format(cal.getTime());
        System.out.println("所在周星期六的日期：" + Saturday);
        dates.add(Saturday);
        cal.add(Calendar.DATE, 1);
        String Sunday = sdf.format(cal.getTime());
        System.out.println("所在周星期日的日期：" + Sunday);
        dates.add(Sunday);
        return dates;
    }

    /**
     * 获取当前月的所有天数
     */
    public List<DateBean> getCalendarMouthAll() {
        String[] split = getDate("yyyy-MM").split("-");
        return getCalendarMouthAll(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    /**
     * 获取固定月上一个月的所有天数
     */
    public List<DateBean> getCalendarPreviousMouthAll(int year, int month) {
        if (month - 1 == 0) {
            year -= 1;
            month = 12;
        } else {
            month -= 1;
        }
        return getCalendarMouthAll(year, month);
    }

    /**
     * 获取当前月上一个月的所有天数
     */
    public List<DateBean> getCalendarPreviousMouthAll() {
        String[] split = getDate("yyyy-MM").split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        if (month - 1 == 0) {
            year -= 1;
            month = 12;
        } else {
            month -= 1;
        }
        return getCalendarMouthAll(year, month);
    }

    /**
     * 获取固定月下一个月的所有天数
     */
    public List<DateBean> getCalendarNextMouthAll(int year, int month) {
        if (month + 1 > 12) {
            year += 1;
            month = 1;
        } else {
            month += 1;
        }
        return getCalendarMouthAll(year, month);
    }

    /**
     * 获取当前月下一个月的所有天数
     */
    public List<DateBean> getCalendarNextMouthAll() {
        String[] split = getDate("yyyy-MM").split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        if (month + 1 > 12) {
            year += 1;
            month = 1;
        } else {
            month += 1;
        }
        return getCalendarMouthAll(year, month);
    }

    /**
     * 获取固定月所有的天数
     *
     * @param year  年
     * @param month 月
     * @return 固定月的日期列表
     */
    public List<DateBean> getCalendarMouthAll(int year, int month) {
        List<DateBean> scheduleBeans = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Calendar calendarMonth = Calendar.getInstance();
            try {
                calendarMonth.setTime(sdf.parse(String.format("%s-%s", year, month)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 获取当月的总天数
            int actualMaximum = calendarMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int j = 1; j < (actualMaximum + 1); j++) {
                DateBean dateBean = new DateBean();

                String monthStr = String.format("%s%s", month < 10 ? 0 : "", month);
                String dayStr = String.format("%s%s", j < 10 ? 0 : "", j);

                dateBean.setYear(year + "");
                dateBean.setMonth(monthStr);
                dateBean.setDay(dayStr);

                String date = String.format("%s-%s-%s", year, monthStr, dayStr);
                dateBean.setDate(date);

                dateBean.setDayOfWeek(getIntWeekOfDay(date));
                dateBean.setDayOfWeekStr(getStrWeekOfDay(date));

                Calendar weekC = Calendar.getInstance();
                weekC.setTime(parseServerTime(date, "yyyy-MM-dd"));
                if (j == 1) {
                    dateBean.setOccupyColumns(weekC.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? 7 : weekC.get(Calendar.DAY_OF_WEEK) - 1);
                } else {
                    dateBean.setOccupyColumns(1);
                }

                scheduleBeans.add(dateBean);
            }
        }
        return scheduleBeans;
    }
}
