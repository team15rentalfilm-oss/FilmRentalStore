package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.rental.*;
import com.iem.FilmRentalStore.entity.*;
import com.iem.FilmRentalStore.mapper.RentalMapper;
import com.iem.FilmRentalStore.repository.*;
import com.iem.FilmRentalStore.service.RentalService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    // 🔥 CREATE RENTAL
    @Override
    @Transactional
    public RentalResponseDTO createRental(RentalRequestDTO request) {

        Inventory inventory = inventoryRepository.findById(request.getInventoryId())
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new EntityNotFoundException("Staff not found"));

        if (rentalRepository.existsByInventoryAndReturnDateIsNull(inventory)) {
            throw new IllegalStateException("Inventory already rented");
        }

        Rental rental = RentalMapper.toEntity(inventory, customer, staff);
        rental.setRentalDate(LocalDateTime.now());

        return RentalMapper.toResponseDTO(rentalRepository.save(rental));
    }

    // 🔥 RETURN RENTAL
    @Override
    @Transactional
    public RentalResponseDTO returnRental(Integer rentalId) {

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        if (rental.getReturnDate() != null) {
            throw new IllegalStateException("Already returned");
        }

        rental.setReturnDate(LocalDateTime.now());

        return RentalMapper.toResponseDTO(rentalRepository.save(rental));
    }

    // 🔥 GET BY ID
    @Override
    @Transactional
    public RentalResponseDTO getRentalById(Integer id) {
        return rentalRepository.findById(id)
                .map(RentalMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));
    }

    // 🔥 PAGINATION SANITIZER (NEW)
    private Pageable sanitizePageable(Pageable pageable) {

        int page = pageable.getPageNumber();
        int size = Math.min(pageable.getPageSize(), 50);

        List<String> allowed = List.of(
                "rentalId",
                "rentalDate",
                "returnDate",
                "lastUpdate"
        );

        Sort safeSort = Sort.unsorted();

        for (Sort.Order order : pageable.getSort()) {
            if (allowed.contains(order.getProperty())) {
                safeSort = safeSort.and(Sort.by(order));
            }
        }

        // ✅ fallback sort
        if (safeSort.isUnsorted()) {
            safeSort = Sort.by(Sort.Direction.DESC, "rentalDate");
        }

        return PageRequest.of(
                Math.max(page, 0),
                size <= 0 ? 10 : size,
                safeSort
        );
    }

    // 🔥 GET ALL
    @Override
    public Page<RentalResponseDTO> getAllRentals(Pageable pageable) {

        pageable = sanitizePageable(pageable); // ✅ FIX

        return rentalRepository.findAll(pageable)
                .map(RentalMapper::toResponseDTO);
    }

    // 🔥 SEARCH BY CUSTOMER NAME
    @Override
    @Transactional
    public List<RentalResponseDTO> getByCustomerName(String name) {
        return rentalRepository
                .findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCase(name, name)
                .stream()
                .map(RentalMapper::toResponseDTO)
                .toList();
    }

    // 🔥 PATCH RENTAL
    @Override
    public RentalResponseDTO patchRental(Integer id, RentalPatchDTO request) {

        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found"));

        if (request.getReturnDate() != null) {
            rental.setReturnDate(request.getReturnDate());
        }

        if (request.getStaffId() != null) {
            Staff staff = staffRepository.findById(request.getStaffId())
                    .orElseThrow(() -> new EntityNotFoundException("Staff not found"));
            rental.setStaff(staff);
        }

        return RentalMapper.toResponseDTO(rentalRepository.save(rental));
    }

    // 🔥 GET BY CUSTOMER ID
    @Override
    public Page<RentalResponseDTO> getByCustomerId(Short customerId, Pageable pageable) {

        pageable = sanitizePageable(pageable); // ✅ FIX

        return rentalRepository.findByCustomer_CustomerId(customerId, pageable)
                .map(RentalMapper::toResponseDTO);
    }

    // 🔥 GET BY INVENTORY ID
    @Override
    public Page<RentalResponseDTO> getByInventoryId(Integer inventoryId, Pageable pageable) {

        pageable = sanitizePageable(pageable); // ✅ FIX

        return rentalRepository.findByInventory_InventoryId(inventoryId, pageable)
                .map(RentalMapper::toResponseDTO);
    }

    // 🔥 GET BY STAFF ID
    @Override
    public Page<RentalResponseDTO> getByStaffId(Byte staffId, Pageable pageable) {

        pageable = sanitizePageable(pageable); // ✅ FIX

        return rentalRepository.findByStaff_StaffId(staffId, pageable)
                .map(RentalMapper::toResponseDTO);
    }
}