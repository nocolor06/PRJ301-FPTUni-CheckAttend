/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;

public class DateTimeHelper {

    public static ArrayList<java.sql.Date> getListDates(java.sql.Date from, java.sql.Date to) {
        ArrayList<java.sql.Date> dates = new ArrayList<>();
        java.sql.Date loop = from;
        while (loop.compareTo(to) <= 0) {
            dates.add(loop);
            java.util.Date d = convertSqlToUtilDate(loop);
            d = addDays(d, 1);
            d = keepOnlyDatePart(d);
            loop = convertUtilToSqlDate(d);
        }
        return dates;
    }

    public static java.util.Date addDays(java.util.Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static java.sql.Date convertUtilToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date convertSqlToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.util.Date keepOnlyDatePart(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static boolean compareDateWithToday(java.sql.Date date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate inputDate = date.toLocalDate();

        int result = currentDate.compareTo(inputDate);

        if (result < 0) {
            return false;//System.out.println(date + " is in the future");
        } else if (result > 0) {
            return false;//System.out.println(date + " is in the past");
        } else {
            return true;//System.out.println(date + " is today");
        }
    }

    public static ArrayList<String> getWeekStartEndDates(int year, String mid) {
        ArrayList<String> weekStartEndDates = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, 1);

        while (date.getYear() == year) {
            LocalDate weekStart = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate weekEnd = weekStart.plusDays(6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            weekStartEndDates.add("Week " + weekStart.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) + " starting on "
//                    + weekStart.format(formatter) + " and ending on " + weekEnd.format(formatter));
            weekStartEndDates.add(weekStart.format(formatter).substring(5) + mid + weekEnd.format(formatter).substring(5));

            date = weekEnd.plusDays(1);
        }
        weekStartEndDates.remove(0);
        return weekStartEndDates;
    }

    public static ArrayList<String> getWeekStartEndDates1(int year, String mid) {
        ArrayList<String> weekStartEndDates = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, 1);

        while (date.getYear() == year) {
            LocalDate weekStart = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate weekEnd = weekStart.plusDays(6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//            weekStartEndDates.add("Week " + weekStart.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) + " starting on "
//                    + weekStart.format(formatter) + " and ending on " + weekEnd.format(formatter));
            weekStartEndDates.add(weekStart.format(formatter).substring(0,5) + mid + weekEnd.format(formatter).substring(0,5));

            date = weekEnd.plusDays(1);
        }
        weekStartEndDates.remove(0);
        return weekStartEndDates;
    }
}
