package com.example.mg.raye7androiddeveloperchallenge.Utils;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    private static SimpleDateFormat destinationFormat =
            new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

    private static SimpleDateFormat timeFormat =
            new SimpleDateFormat("h:mm:ss a", Locale.getDefault());

    private static SimpleDateFormat sourceFormat =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());

    public static String destinationDate(@NonNull Date date) {
        return destinationFormat.format(date);
    }

    public static String timeDate(@NonNull String date) throws ParseException {
        Date parse = sourceFormat.parse(date);
        return timeFormat.format(parse);
    }

    public static Date sourceDate(String date) throws ParseException {
        Date parse = sourceFormat.parse(date);
        return destinationDate(destinationDate(parse));
    }

    private static Date destinationDate(String date) throws ParseException {
        return destinationFormat.parse(date);
    }

}