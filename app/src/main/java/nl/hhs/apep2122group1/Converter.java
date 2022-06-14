package nl.hhs.apep2122group1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Converter {

    public static String dateStampToString(LocalDateTime dateStamp) {
        if (dateStamp != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
            String dateString = formatter.format(dateStamp);
            return dateString;
        }
        return "No deadline"; // TODO: make resource
    }
}