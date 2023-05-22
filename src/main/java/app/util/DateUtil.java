package app.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    public static String getDateInString(LocalDate date) {
        DateTimeFormatter formatter = getDefaultFormatter();
        String dateInStr = date.format(formatter);
        return dateInStr;
    }

    public static LocalDate get(String dateInString) throws DateTimeParseException {
        DateTimeFormatter formatter = getDefaultFormatter();
        LocalDate date = LocalDate.parse(dateInString, formatter);
        return date;
    }

    public static LocalDate subtractDays(LocalDate date, int days) {
        LocalDate nextDate = date.minus(Period.ofDays(days));
        return nextDate;
    }

    public static LocalDate addMonths(LocalDate date, int months) {
        LocalDate nextDate = date.plus(Period.ofMonths(months));
        return nextDate;
    }

    private static DateTimeFormatter getDefaultFormatter() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

}
