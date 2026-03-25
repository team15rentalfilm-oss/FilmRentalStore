package com.iem.FilmRentalStore.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";

    // Convert LocalDate to String
    public static String formatDate(LocalDate date) {
        return formatDate(date, DEFAULT_PATTERN);
    }

    public static String formatDate(LocalDate date, String pattern) {
        if (date == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    // Convert String to LocalDate
    public static LocalDate parseDate(String dateStr) {
        return parseDate(dateStr, DEFAULT_PATTERN);
    }

    public static LocalDate parseDate(String dateStr, String pattern) {
        if (dateStr == null || dateStr.isEmpty()) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateStr, formatter);
    }
}