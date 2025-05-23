package com.sis.inventoryapp.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    public static Long findTimeDifferenceInDays(Date creationTimestamp) {
        LocalDateTime now = LocalDateTime.now();
        Date nowDate = java.util.Date
                .from(now.atZone(ZoneId.systemDefault()).toInstant());
        long diffInMillies = Math.abs(nowDate.getTime() - creationTimestamp.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static Instant setHistoricCreationDate(int days) {
        Instant instant = Instant.now();
        return instant.minus(Period.ofDays(days));
    }
}
