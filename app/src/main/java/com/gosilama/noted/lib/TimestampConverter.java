package com.gosilama.noted.lib;

import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimestampConverter {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    @TypeConverter
    public static Date fromTimeStamp(String value) {
        if (value != null) {
            try {
                TimeZone timeZone = TimeZone.getTimeZone("Africa/Lagos");
                dateFormat.setTimeZone(timeZone);
                return dateFormat.parse(value);
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            return null;
        }

        return null;
    }

    @TypeConverter
    public static String dateToTimeStamp(Date date) {
        TimeZone timeZone = TimeZone.getTimeZone("Africa/Lagos");
        dateFormat.setTimeZone(timeZone);
        return date == null ? null : dateFormat.format(date);
    }
}
