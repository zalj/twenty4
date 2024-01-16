package gpt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;

public class TimeStringIncrement {

    public static void main(String[] args) {
        String input = "2023-11-31 23:23:15";
        String output = incrementTimeString(input);
        System.out.println(output);
    }

    public static String incrementTimeString(String input) {
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("uuuu-MM-dd HH:mm:ss")
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter()
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            int seconds = dateTime.getSecond();
            if (seconds >= 30) {
                dateTime = dateTime.plusMinutes(1);
            }
            return dateTime.withSecond(0).format(formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time string format.");
            return null;
        }
    }
}


