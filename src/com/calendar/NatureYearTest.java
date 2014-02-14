package com.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NatureYearTest {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse("2013-2-28");
        Date date2 = dateFormat.parse("2012-2-1");
        System.out.println("date1:" + dateFormat.format(date1));
        System.out.println("date2:" + dateFormat.format(date2));
        System.out.println("result:" + inOneNatureYear(date1,date2));

    }

    public static boolean inOneNatureYear(Date oneDate, Date secondDate) {
        boolean inOneNatureYear = false;
        Calendar oneCalendar = Calendar.getInstance();
        oneCalendar.setTime(oneDate);
        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(secondDate);

        if (oneCalendar.equals(secondCalendar)) {
            inOneNatureYear = true;
        }
        else if (oneCalendar.before(secondCalendar)) {
            oneCalendar.add(Calendar.YEAR, 1);
            if (oneCalendar.after(secondCalendar) || oneCalendar.equals(secondCalendar)) {
                inOneNatureYear = true;
            }
        }
        else {
            secondCalendar.add(Calendar.YEAR, 1);
            if (secondCalendar.after(oneCalendar) || secondCalendar.equals(oneCalendar)) {
                inOneNatureYear = true;
            }
        }
        return inOneNatureYear;
    }

}
