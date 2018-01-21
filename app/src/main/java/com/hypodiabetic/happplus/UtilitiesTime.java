package com.hypodiabetic.happplus;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tim on 24/02/2017.
 * Shared Time Utility Functions
 */

public class UtilitiesTime {

    public static String displayAge(Date timestamp) {
        int minutesAgo = (int) Math.floor(getDiffInMins(timestamp, new Date()));
        switch (minutesAgo) {
            case 0:
                return MainApp.getInstance().getString(R.string.time_just_now);
            case 1:
                return minutesAgo + " " + MainApp.getInstance().getString(R.string.time_min_ago);
            case 60:
                return "1 " + MainApp.getInstance().getString(R.string.time_hour_ago);
            default:
                if (minutesAgo < 60) {
                    return minutesAgo + " " + MainApp.getInstance().getString(R.string.time_mins_ago);
                } else {
                    return Utilities.round((double) minutesAgo / 60, 1) + " " + MainApp.getInstance().getString(R.string.time_hours_ago);
                }
        }
    }

    public static String displayTimeRemaing(Integer mins){
        switch (mins) {
            case 0:
            case 1:
                return mins + " " + MainApp.getInstance().getString(R.string.time_min);
            case 60:
                return "1 " + MainApp.getInstance().getString(R.string.time_hour);
            default:
                if (mins < 60) {
                    return mins + " " + MainApp.getInstance().getString(R.string.time_mins);
                } else {
                    return Utilities.round((double) mins / 60, 1) + " " + MainApp.getInstance().getString(R.string.time_hours);
                }
        }
    }

    public static double getDiffInMins(Date timestampFrom, Date timestampTo) {
        return (timestampTo.getTime() - timestampFrom.getTime()) /(1000*60);
    }

    public static Date getDateHoursAgo(Date date, int hours){
        return new Date(date.getTime() - ((60000 * 60 * hours)));
    }
    public static Date getDateMinsAhead(Date date, int mins){
        return new Date(date.getTime() + (TimeUnit.MINUTES.toMillis(mins)));
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis()+calendar.getTimeZone().getOffset(calendar.getTimeInMillis()));
    }

    public static Date getEndOfDay(Date date) {
        // Add one day's time to the beginning of the day.
        // 24 hours * 60 minutes * 60 seconds * 1000 milliseconds = 1 day
        return new Date(getStartOfDay(date).getTime() + (24 * 60 * 60 * 1000) - 1000);
    }


}
