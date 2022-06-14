package nl.hhs.apep2122group1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Locale;

public class ConverterTests {

    @Test
    public void timeStampToString_NL_should_return_correct_string() {
        // ARRANGE:
        LocalDateTime timeStamp = LocalDateTime.of(2022, 6, 14, 14, 00);
        Locale.setDefault(new Locale("nl"));

        // ACT:
        String result = Converter.timeStampToString(timeStamp);
        String expected = "14 jun. 2022 14:00";

        // ASSERT:
        assertEquals(expected, result);
    }

    @Test
    public void timeStampToString_EN_should_return_correct_string() {
        // ARRANGE:
        LocalDateTime timeStamp = LocalDateTime.of(2022, 6, 14, 14, 00);
        Locale.setDefault(new Locale("en"));

        // ACT:
        String result = Converter.timeStampToString(timeStamp);
        String expected = "Jun 14, 2022, 2:00 PM";

        // ASSERT:
        assertEquals(expected, result);
    }

    @Test
    public void timeStampToString_null_should_return_null() { // check
        // ARRANGE:

        // ACT:

        // ASSERT:
        assertNull(Converter.timeStampToString(null));
    }
}
