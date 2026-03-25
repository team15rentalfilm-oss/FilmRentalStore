package com.iem.FilmRentalStore.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FeeCalculator {

    private static final BigDecimal DAILY_FEE = BigDecimal.valueOf(2.5);

    // Calculate rental fee based on days rented
    public static BigDecimal calculateRentalFee(LocalDate rentDate, LocalDate returnDate) {
        if (rentDate == null || returnDate == null) return BigDecimal.ZERO;

        long days = ChronoUnit.DAYS.between(rentDate, returnDate);
        if (days < 0) days = 0;

        return DAILY_FEE.multiply(BigDecimal.valueOf(days));
    }
}