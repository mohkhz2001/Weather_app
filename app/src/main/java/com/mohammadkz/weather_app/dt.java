package com.mohammadkz.weather_app;

import android.os.Build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class dt {

    public static void main(String[] args) throws Exception {
        long sunrise = 1611563400, sunset = 1611593945, currentTime = 1611563400;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            System.out.println(formatTime_show(Instant.ofEpochSecond(currentTime)));
        }
        System.out.println(formatTime(Instant.ofEpochSecond(sunrise)));
        System.out.println(formatTime(Instant.ofEpochSecond(sunset)));


    }

    static String formatTime(Instant time) {
        return formatter.format(time);
    }

    static String formatTime_show(Instant time) {
        return formatter_show.format(time);
    }

    static String get_day(Instant time) {
        return formatter_show.format(time);
    }

    static String getMonth(Instant time) {
        return formatter_show.format(time);
    }


    static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd h:mm:ss a", Locale.ENGLISH)
            .withZone(ZoneId.of("Asia/Tehran"));

    static final DateTimeFormatter formatter_show = DateTimeFormatter
            .ofPattern("E,c'th' MMMM", Locale.ENGLISH)
            .withZone(ZoneId.of("Asia/Tehran"));
}
