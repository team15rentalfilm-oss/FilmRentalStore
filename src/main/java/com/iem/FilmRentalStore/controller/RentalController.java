package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<RentalDTO>> getRentals(
            @RequestParam(required = false) Short customerId,
            @RequestParam(required = false) Integer inventoryId,
            @RequestParam(required = false) Integer staffId
    ) {
        if (customerId != null) {
            return ResponseEntity.ok(rentalService.getRentalsByCustomerId(customerId));
        }
        if (inventoryId != null) {
            return ResponseEntity.ok(rentalService.getRentalsByInventoryId(inventoryId));
        }
        if (staffId != null) {
            return ResponseEntity.ok(rentalService.getRentalsByStaffId(staffId));
        }
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable Integer id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @PostMapping
    public ResponseEntity<RentalDTO> createRental(@Valid @RequestBody RentalDTO rentalDTO) {
        return new ResponseEntity<>(rentalService.createRental(rentalDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalDTO> updateRental(@PathVariable Integer id, @Valid @RequestBody RentalDTO rentalDTO) {
        return ResponseEntity.ok(rentalService.updateRental(id, rentalDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Integer id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
}
