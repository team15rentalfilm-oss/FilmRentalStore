package com.iem.FilmRentalStore.service;

import java.util.List;

public interface RentalService {

    RentalDTO createRental(RentalDTO rentalDTO);
    RentalDTO getRentalById(Integer id);
    List<RentalDTO> getAllRentals();
    RentalDTO updateRental(Integer id, RentalDTO rentalDTO);
    void deleteRental(Integer id);
    List<RentalDTO> getRentalsByCustomerId(Short customerId);
    List<RentalDTO> getRentalsByInventoryId(Integer inventoryId);
    List<RentalDTO> getRentalsByStaffId(Integer staffId);
}
