package com.mossco.za.mvpapp.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringsUtils {

    public static String getFormattedDate(String inputDate)  {
        long unixDate = Long.parseLong(inputDate.substring(inputDate.indexOf('(')+1,inputDate.indexOf('+')));
        Date date = new Date(unixDate);
        final String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.US);

        return simpleDateFormat.format(date);
    }
    public static String getFormattedDateWithTime(String inputDate)  {
        long unixDate = Long.parseLong(inputDate.substring(inputDate.indexOf('(')+1,inputDate.indexOf('+')));
        Date date = new Date(unixDate);
        final String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.US);

        return simpleDateFormat.format(date);
    }

    //public static String get
}
