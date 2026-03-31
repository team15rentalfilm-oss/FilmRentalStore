package com.iem.FilmRentalStore.dto.payment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentResponseDTO {

    private Integer paymentId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;

    private Integer rentalId;

    private Short staffId;
    private String staffName;

    private Short customerId;
    private String customerName;
}
