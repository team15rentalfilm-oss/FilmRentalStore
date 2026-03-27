package com.iem.FilmRentalStore.dto.inventory;

import jakarta.validation.constraints.NotNull;
import lombok.Setter;


import lombok.Getter;

@Getter
@Setter
public class InventoryRequestDTO {

    @NotNull(message = "Film ID is required")
    private Short filmId;

    @NotNull(message = "Store ID is required")
    private Short storeId;
}