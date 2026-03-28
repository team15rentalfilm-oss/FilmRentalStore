package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.rental.RentalDTO;
import com.iem.FilmRentalStore.dto.rental.RentalRequestDTO;
import com.iem.FilmRentalStore.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    // 🔥 CREATE RENTAL
    @PostMapping
    public RentalDTO createRental(@Valid @RequestBody RentalRequestDTO request) {
        return rentalService.createRental(request);
    }

    // 🔥 RETURN RENTAL
    @PostMapping("/{id}/return")
    public RentalDTO returnRental(@PathVariable Integer id) {
        return rentalService.returnRental(id);
    }

    // 🔥 GET BY ID
    @GetMapping("/{id}")
    public RentalDTO getRentalById(@PathVariable Integer id) {
        return rentalService.getRentalById(id);
    }

    // 🔥 GET ALL
    @GetMapping
    public List<RentalDTO> getAllRentals() {
        return rentalService.getAllRentals();
    }
}