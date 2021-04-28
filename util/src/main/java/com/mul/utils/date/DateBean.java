package com.mul.utils.date;

/**
 * @ProjectName: utils
 * @Package: com.mul.utils.date
 * @ClassName: DateBean
 * @Author: zdd
 * @CreateDate: 2020/4/22 14:56
 * @Description: 日期数据源
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/22 14:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class DateBean {
    private String year; // 年
    private String month; // 月
    private String day; // 日
    private String date; // 时间
    private int dayOfWeek; // 周几数字
    private String dayOfWeekStr; // 周几大写数字
    private int occupyColumns; // 占据多少行

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayOfWeekStr() {
        return dayOfWeekStr;
    }

    public void setDayOfWeekStr(String dayOfWeekStr) {
        this.dayOfWeekStr = dayOfWeekStr;
    }

    public int getOccupyColumns() {
        return occupyColumns;
    }

    public void setOccupyColumns(int occupyColumns) {
        this.occupyColumns = occupyColumns;
    }
}
