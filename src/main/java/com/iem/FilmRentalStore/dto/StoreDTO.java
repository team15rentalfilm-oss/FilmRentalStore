package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

    private Byte storeId;

    @NotNull(message = "Manager staff id is required")
    private Byte managerStaffId;

    @NotNull(message = "Address id is required")
    private Short addressId;

    private LocalDateTime lastUpdate;
}