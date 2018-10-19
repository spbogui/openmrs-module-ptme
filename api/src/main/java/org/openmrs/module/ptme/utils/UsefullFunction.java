package org.openmrs.module.ptme.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UsefullFunction {
    public static Date formatDateToyyyyMMdd(Date d){
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            //noinspection UnnecessaryLocalVariable
            Date ddf = formatter.parse(formatter.format(d));
            return ddf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    //Convert Calendar to Date
    public static Date calendarToDate(Calendar calendar) {
        return calendar.getTime();
    }

    public static Date formatDateToddMMyyyy(Date d){
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            //noinspection UnnecessaryLocalVariable
            Date ddf = formatter.parse(formatter.format(d));
            return ddf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date getFirstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static String formatDateToyyyyMMddString(Date d) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date ddf = formatter.parse(formatter.format(d));
            return ddf.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d.toString();
    }

    public static Date formatDateToddMMyyyyhms(Date d){
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //noinspection UnnecessaryLocalVariable
            Date ddf = formatter.parse(formatter.format(d));
            return ddf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date formatDateToddMMyyyyhmsStart(Date d){
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 00:00:00");
            //noinspection UnnecessaryLocalVariable
            Date ddf = formatter.parse(formatter.format(d));
            return ddf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date formatDateToddMMyyyyhmsEnd(Date d){
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 23:59:59");
            //noinspection UnnecessaryLocalVariable
            Date ddf = formatter.parse(formatter.format(d));
            return ddf;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
