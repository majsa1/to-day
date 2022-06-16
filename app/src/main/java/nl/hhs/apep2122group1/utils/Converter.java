package nl.hhs.apep2122group1.utils;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Converter {
    public static String timeStampToString(LocalDateTime timeStamp) {
        if (timeStamp != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
            return formatter.format(timeStamp);
        }
        return null;
    }

    @TypeConverter
    public static LocalDateTime LocalDateTimeFromIso8601String(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString);
    }

    @TypeConverter
    public static String iso8601StringFromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.toString();
    }
}