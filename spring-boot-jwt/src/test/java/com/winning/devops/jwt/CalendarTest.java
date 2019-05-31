package com.winning.devops.jwt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chensj
 * @date 2019-05-30 13:02
 */
public class CalendarTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(calendar.getTime()));
        calendar.add(Calendar.SECOND, 3600);
        Date time = calendar.getTime();
        System.out.println(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(time));
    }
}
