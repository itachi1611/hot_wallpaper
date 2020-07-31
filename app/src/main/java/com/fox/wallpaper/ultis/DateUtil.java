package com.fox.wallpaper.ultis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    /**
     *
     * @param millSeconds
     * @param format
     * @return
     */
    public static String getDateStr(long millSeconds, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        String dateStr = formatter.format(new Date(millSeconds));
        return dateStr;
    }

}
