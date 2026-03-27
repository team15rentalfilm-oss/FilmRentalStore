package com.iem.FilmRentalStore.dto.rental;

import java.time.LocalDateTime;

import com.iem.FilmRentalStore.dto.customer.CustomerDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryDTO;
import com.iem.FilmRentalStore.dto.staff.StaffDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentalResponseDTO {

    private Integer rentalId;

    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    private InventoryDTO inventory;
    private CustomerDTO customer;
    private StaffDTO staff;
    private LocalDateTime lastUpdate;
}