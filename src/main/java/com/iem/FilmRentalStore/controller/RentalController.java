package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.rental.*;
import com.iem.FilmRentalStore.service.RentalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public RentalResponseDTO createRental(@Valid @RequestBody RentalRequestDTO request) {
        return rentalService.createRental(request);
    }

    @PostMapping("/{id}/return")
    public RentalResponseDTO returnRental(@PathVariable Integer id) {
        return rentalService.returnRental(id);
    }

    @GetMapping("/{id}")
    public RentalResponseDTO getRentalById(@PathVariable Integer id) {
        return rentalService.getRentalById(id);
    }

    @GetMapping
    public Page<RentalResponseDTO> getAllRentals(
            @PageableDefault(size = 10) Pageable pageable) {
        return rentalService.getAllRentals(pageable);
    }

    @GetMapping("/search/customer")
    public List<RentalResponseDTO> getByCustomerName(@RequestParam String name) {
        return rentalService.getByCustomerName(name);
    }

    @GetMapping("/customer/{customerId}")
    public Page<RentalResponseDTO> getByCustomerId(
            @PathVariable Short customerId,
            @PageableDefault(size = 10) Pageable pageable) {
        return rentalService.getByCustomerId(customerId, pageable);
    }

    @GetMapping("/inventory/{inventoryId}")
    public Page<RentalResponseDTO> getByInventoryId(
            @PathVariable Integer inventoryId,
            @PageableDefault(size = 10) Pageable pageable) {
        return rentalService.getByInventoryId(inventoryId, pageable);
    }

    @GetMapping("/staff/{staffId}")
    public Page<RentalResponseDTO> getByStaffId(
            @PathVariable Byte staffId,
            @PageableDefault(size = 10) Pageable pageable) {
        return rentalService.getByStaffId(staffId, pageable);
    }
}