package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.rental.RentalDTO;
import com.iem.FilmRentalStore.dto.rental.RentalRequestDTO;

import java.util.List;

public interface RentalService {

    RentalDTO createRental(RentalRequestDTO request);

    RentalDTO returnRental(Integer rentalId);

    RentalDTO getRentalById(Integer id);

    List<RentalDTO> getAllRentals();
}