package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {

    private Integer rentalId;

    @NotNull(message = "Rental date is mandatory")
    private LocalDateTime rentalDate;

    @NotNull(message = "Inventory ID is mandatory")
    @Positive(message = "Inventory ID must be greater than 0")
    private Integer inventoryId;

    @NotNull(message = "Customer ID is mandatory")
    @Positive(message = "Customer ID must be greater than 0")
    private Short customerId;

    private LocalDateTime returnDate;

    @NotNull(message = "Staff ID is mandatory")
    @Positive(message = "Staff ID must be greater than 0")
    private Integer staffId;

    private LocalDateTime lastUpdate;
}
