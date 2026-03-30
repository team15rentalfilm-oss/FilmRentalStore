package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.rental.*;
import com.iem.FilmRentalStore.service.RentalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    // 🔥 CREATE RENTAL
    @PostMapping
    public RentalResponseDTO createRental(@Valid @RequestBody RentalRequestDTO request) {
        return rentalService.createRental(request);
    }

    // 🔥 RETURN RENTAL
    @PostMapping("/{id}/return")
    public RentalResponseDTO returnRental(@PathVariable Integer id) {
        return rentalService.returnRental(id);
    }

    // 🔥 GET BY ID
    @GetMapping("/{id}")
    public RentalResponseDTO getRentalById(@PathVariable Integer id) {
        return rentalService.getRentalById(id);
    }

    // ✅ FIXED: PAGINATION ADDED
    @GetMapping
    public Page<RentalResponseDTO> getAllRentals(Pageable pageable) {
        return rentalService.getAllRentals(pageable);
    }

    // 🔥 SEARCH BY CUSTOMER NAME
    @GetMapping("/search/customer")
    public List<RentalResponseDTO> getByCustomerName(@RequestParam String name) {
        return rentalService.getByCustomerName(name);
    }

    // 🔥 GET BY CUSTOMER ID
    @GetMapping("/customer/{customerId}")
    public Page<RentalResponseDTO> getByCustomerId(
            @PathVariable Short customerId,
            Pageable pageable) {

        return rentalService.getByCustomerId(customerId, pageable);
    }

    // 🔥 GET BY INVENTORY ID
    @GetMapping("/inventory/{inventoryId}")
    public Page<RentalResponseDTO> getByInventoryId(
            @PathVariable Integer inventoryId,
            Pageable pageable) {

        return rentalService.getByInventoryId(inventoryId, pageable);
    }

    // 🔥 GET BY STAFF ID
    @GetMapping("/staff/{staffId}")
    public Page<RentalResponseDTO> getByStaffId(
            @PathVariable Byte staffId,
            Pageable pageable) {

        return rentalService.getByStaffId(staffId, pageable);
    }
}