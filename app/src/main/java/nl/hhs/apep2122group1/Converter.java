package nl.hhs.apep2122group1;

import android.content.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Converter {

    public static String dateStampToString(Context context, LocalDateTime dateStamp) {
        if (dateStamp != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
            String dateString = formatter.format(dateStamp);
            return dateString;
        }
        return context.getResources().getString(R.string.no_deadline_text); // TODO: make resource
    }
}