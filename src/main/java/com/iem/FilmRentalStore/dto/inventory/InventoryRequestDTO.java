package com.iem.FilmRentalStore.dto.inventory;

import jakarta.validation.constraints.NotNull;
import lombok.Setter;


import lombok.Getter;

@Getter
@Setter
public class InventoryRequestDTO {

    private Short filmId;
    private Short storeId;
}