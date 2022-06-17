package nl.hhs.apep2122group1;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Locale;

import nl.hhs.apep2122group1.utils.Converter;

public class ConverterTests {

    @Test
    public void timeStampToString_NL_returns_correct_string() {
        // ARRANGE:
        LocalDateTime timeStamp = LocalDateTime.of(2022, 6, 14, 14, 10);
        Locale.setDefault(new Locale("nl"));

        // ACT:
        String expected = "14 jun. 2022 14:10";
        String result = Converter.timeStampToReadableString(timeStamp);

        // ASSERT:
        Assert.assertEquals(expected, result);
    }

    @Test
    public void timeStampToString_EN_returns_correct_string() {
        // ARRANGE:
        LocalDateTime timeStamp = LocalDateTime.of(2022, 6, 14, 14, 10);
        Locale.setDefault(new Locale("en"));

        // ACT:
        String expected = "Jun 14, 2022, 2:10 PM";
        String result = Converter.timeStampToReadableString(timeStamp);

        // ASSERT:
        Assert.assertEquals(expected, result);
    }

    @Test
    public void inputStringToTimeStamp_returns_correct_string() {
        // ARRANGE:
        LocalDateTime timeStamp = LocalDateTime.of( 2022,6, 14, 14, 10);
        String input = "2022-06-14 14:10";

        // ACT:
        String expected = "14 jun. 2022 14:10";
        LocalDateTime result = Converter.inputStringToTimeStamp(input);

        // ASSERT:
        Assert.assertEquals(expected, result);
    }

    @Test
    public void LocalDateTimeFromIso8601String_returns_time_returns_incorrect_string() {
        //ARRANGE
        LocalDateTime timeStamp = LocalDateTime.of(2022, 6, 14, 14, 10);
        String input = null;

        // ACT:
        String expected = "14 jun. 2022 14:10";
        LocalDateTime result = Converter.LocalDateTimeFromIso8601String(input);

        // ASSERT:
        Assert.assertNotEquals(expected, result);
    }
}
