package com.xh.readapp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    private static final DateTimeFormatter DATETIMEFORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private TimeUtil(){}

    public static String getStringTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return DATETIMEFORMATTER.format(localDateTime);
    }

    public static LocalDateTime getLocalDateTime(String time){
        return LocalDateTime.parse(time,DATETIMEFORMATTER);
    }
}
