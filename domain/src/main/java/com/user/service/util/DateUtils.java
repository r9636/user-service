package com.user.service.util;

import java.time.ZonedDateTime;

public class DateUtils {
    private static volatile DateUtils dateUtils = null;

    private DateUtils() {
    }

    public static DateUtils getInstance() {
        if (dateUtils == null) {
            synchronized (DateUtils.class) {
                dateUtils = new DateUtils();
            }
        }
        return dateUtils;
    }

    public ZonedDateTime getCurrentTime() {
        return ZonedDateTime.now();
    }
}
