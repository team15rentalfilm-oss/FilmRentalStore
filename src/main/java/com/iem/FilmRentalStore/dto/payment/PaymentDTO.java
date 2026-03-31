package com.iem.FilmRentalStore.dto.payment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class PaymentDTO {

    private BigDecimal amount;
    private String paymentDate;

    private String customerName;
    private String filmTitle;
}
