package nl.hhs.apep2122group1.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Converter {

    public static String timeStampToString(LocalDateTime timeStamp) {
        if (timeStamp != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
            String dateString = formatter.format(timeStamp);
            return dateString;
        }
        return null;
    }
}