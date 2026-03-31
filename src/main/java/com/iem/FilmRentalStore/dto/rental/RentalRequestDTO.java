package com.iem.FilmRentalStore.dto.rental;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalRequestDTO {

    @NotNull(message = "Inventory ID is required")
    private Integer inventoryId;

    @NotNull(message = "Customer ID is required")
    private Short customerId;

    private Short staffId;
}
