package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.rental.*;
import com.iem.FilmRentalStore.entity.*;

public class RentalMapper {

    // 🔹 Entity creation (no dto needed)
    public static Rental toEntity(Inventory inventory,
                                  Customer customer,
                                  Staff staff) {

        Rental rental = new Rental();
        rental.setInventory(inventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);
        return rental;
    }

    // 🔹 Lightweight DTO (matches your RentalDTO)
    public static RentalDTO toDTO(Rental rental) {
        RentalDTO dto = new RentalDTO();

        dto.setFilmTitle(rental.getInventory().getFilm().getTitle());
        dto.setStoreName("Store " + rental.getInventory().getStore().getStoreId());

        dto.setRentalDate(rental.getRentalDate().toString());
        dto.setReturnDate(
                rental.getReturnDate() != null
                        ? rental.getReturnDate().toString()
                        : null
        );

        return dto;
    }

    // 🔹 Detailed Response DTO
    public static RentalResponseDTO toResponseDTO(Rental rental) {
        RentalResponseDTO dto = new RentalResponseDTO();

        dto.setRentalId(rental.getRentalId());
        dto.setRentalDate(rental.getRentalDate());
        dto.setReturnDate(rental.getReturnDate());
        dto.setLastUpdate(rental.getLastUpdate());

        dto.setInventory(InventoryMapper.toDTO(rental.getInventory()));
        dto.setCustomer(CustomerMapper.toDTO(rental.getCustomer()));
        dto.setStaff(StaffMapper.toDTO(rental.getStaff()));

        return dto;
    }
}