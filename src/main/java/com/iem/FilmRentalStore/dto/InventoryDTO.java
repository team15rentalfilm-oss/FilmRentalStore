package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

    private Integer inventoryId;

    @NotNull
    private Short filmId;

    @NotNull
    private Byte storeId;

    private LocalDateTime lastUpdate;
}