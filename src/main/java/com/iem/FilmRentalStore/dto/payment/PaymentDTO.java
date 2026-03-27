package com.iem.FilmRentalStore.dto.payment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO {

    private BigDecimal amount;
    private LocalDateTime paymentDate;

    private String customerName;
    private String filmTitle;
}