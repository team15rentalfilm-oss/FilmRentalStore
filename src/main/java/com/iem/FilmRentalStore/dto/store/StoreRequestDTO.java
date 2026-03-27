package com.iem.FilmRentalStore.dto.store;

import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;


@Getter
@Setter
public class StoreRequestDTO {

    @NotNull(message = "Manager is required")
    private Integer managerStaffId; // practical: staff already exists

    @NotNull(message = "Address is required")
    @Valid
    private AddressRequestDTO address;
}