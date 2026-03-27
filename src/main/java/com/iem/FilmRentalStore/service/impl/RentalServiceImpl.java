package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.rental.RentalDTO;
import com.iem.FilmRentalStore.dto.rental.RentalRequestDTO;
import com.iem.FilmRentalStore.entity.*;
import com.iem.FilmRentalStore.mapper.RentalMapper;
import com.iem.FilmRentalStore.repository.*;
import com.iem.FilmRentalStore.service.RentalService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    @Override
    public RentalDTO createRental(RentalRequestDTO request) {

        // 🔥 Step 1: Fetch dependencies
        Inventory inventory = inventoryRepository.findById(request.getInventoryId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Inventory not found with id: " + request.getInventoryId()));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Customer not found with id: " + request.getCustomerId()));

        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Staff not found with id: " + request.getStaffId()));

        // 🔥 Step 2: Check if inventory already rented
        boolean isRented = rentalRepository
                .existsByInventoryAndReturnDateIsNull(inventory);

        if (isRented) {
            throw new IllegalStateException("Inventory is already rented");
        }

        // 🔥 Step 3: Create rental
        Rental rental = RentalMapper.toEntity(inventory, customer, staff);        rental.setRentalDate(LocalDateTime.now());

        Rental saved = rentalRepository.save(rental);

        return RentalMapper.toDTO(saved);
    }

    @Override
    public RentalDTO returnRental(Integer rentalId) {

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Rental not found with id: " + rentalId));

        if (rental.getReturnDate() != null) {
            throw new IllegalStateException("Rental already returned");
        }

        rental.setReturnDate(LocalDateTime.now());

        Rental updated = rentalRepository.save(rental);

        return RentalMapper.toDTO(updated);
    }

    @Override
    public RentalDTO getRentalById(Integer id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + id));

        return RentalMapper.toDTO(rental);
    }

    @Override
    public List<RentalDTO> getAllRentals() {
        return rentalRepository.findAll()
                .stream()
                .map(RentalMapper::toDTO)
                .toList();
    }
}