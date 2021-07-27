package com.xianguoliang.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static void main(String[] args) throws ParseException {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> dateList = getBetweenDates(sdf.parse("2019-05-06"), sdf.parse("2019-05-12"));
        for (Date date : dateList) {
            System.out.println(sdf.format(date) + " " + dateToWeek(sdf.format(date)));
        }
    }


    /**
     * 获取日期间日期
     * @param start
     * @param end
     * @return
     */
    private static List<Date> getBetweenDates(Date start, Date end) {

        List<Date> result = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        tempEnd.add(Calendar.DAY_OF_YEAR, 1);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     * 根据日期获取 星期 （2021-06-27 ——> 星期日）
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = f.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
