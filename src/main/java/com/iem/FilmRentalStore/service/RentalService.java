package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.rental.*;
import org.springframework.data.domain.*;

import java.util.List;

public interface RentalService {

    RentalResponseDTO createRental(RentalRequestDTO request);

    RentalResponseDTO returnRental(Integer rentalId);

    RentalResponseDTO getRentalById(Integer id);

    Page<RentalResponseDTO> getAllRentals(Pageable pageable);

    List<RentalResponseDTO> getByCustomerName(String name);

    RentalResponseDTO patchRental(Integer id, RentalPatchDTO request);

    Page<RentalResponseDTO> getByCustomerId(Short customerId, Pageable pageable);

    Page<RentalResponseDTO> getByInventoryId(Integer inventoryId, Pageable pageable);

    Page<RentalResponseDTO> getByStaffId(Byte staffId, Pageable pageable);
}
