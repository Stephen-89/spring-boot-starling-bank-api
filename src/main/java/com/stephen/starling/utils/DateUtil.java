package com.stephen.starling.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtil {

    final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static String getCurrentDate() {
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        return simpleDateFormat.format(timestamp);
    }

    public static String getCurrentDateMinusDays(int days) {
        Date currentDate = new Date();
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                .minusDays(days);
        Date currentMinusDays = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Timestamp timestamp = new Timestamp(currentMinusDays.getTime());
        return simpleDateFormat.format(timestamp);
    }
    
}
