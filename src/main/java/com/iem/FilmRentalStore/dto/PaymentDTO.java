package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Short paymentId;

    @NotNull(message = "Customer ID is mandatory")
    @Positive(message = "Customer ID must be greater than 0")
    private Short customerId;

    @NotNull(message = "Staff ID is mandatory")
    @Positive(message = "Staff ID must be greater than 0")
    private Integer staffId;

    @NotNull(message = "Rental ID is mandatory")
    @Positive(message = "Rental ID must be greater than 0")
    private Integer rentalId;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    private LocalDateTime paymentDate;
    private LocalDateTime lastUpdate;
}
