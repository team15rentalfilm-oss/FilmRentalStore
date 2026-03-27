package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.entity.Rental;
import com.iem.FilmRentalStore.exception.ResourceNotFoundException;
import com.iem.FilmRentalStore.repository.CustomerRepository;
import com.iem.FilmRentalStore.repository.InventoryRepository;
import com.iem.FilmRentalStore.repository.RentalRepository;
import com.iem.FilmRentalStore.repository.StaffRepository;
import com.iem.FilmRentalStore.service.RentalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;

    public RentalServiceImpl(
            RentalRepository rentalRepository,
            InventoryRepository inventoryRepository,
            CustomerRepository customerRepository,
            StaffRepository staffRepository
    ) {
        this.rentalRepository = rentalRepository;
        this.inventoryRepository = inventoryRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public RentalDTO createRental(RentalDTO rentalDTO) {
        validateReferences(rentalDTO);
        return toDTO(rentalRepository.save(toEntity(rentalDTO)));
    }

    @Override
    @Transactional(readOnly = true)
    public RentalDTO getRentalById(Integer id) {
        return toDTO(findRental(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentalDTO> getAllRentals() {
        return rentalRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public RentalDTO updateRental(Integer id, RentalDTO rentalDTO) {
        validateReferences(rentalDTO);

        Rental rental = findRental(id);
        rental.setRentalDate(rentalDTO.getRentalDate());
        rental.setInventoryId(rentalDTO.getInventoryId());
        rental.setCustomerId(rentalDTO.getCustomerId());
        rental.setReturnDate(rentalDTO.getReturnDate());
        rental.setStaffId(rentalDTO.getStaffId());

        return toDTO(rentalRepository.save(rental));
    }

    @Override
    public void deleteRental(Integer id) {
        rentalRepository.delete(findRental(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentalDTO> getRentalsByCustomerId(Short customerId) {
        ensureCustomerExists(customerId);
        return rentalRepository.findByCustomerId(customerId).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentalDTO> getRentalsByInventoryId(Integer inventoryId) {
        ensureInventoryExists(inventoryId);
        return rentalRepository.findByInventoryId(inventoryId).stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentalDTO> getRentalsByStaffId(Integer staffId) {
        ensureStaffExists(staffId);
        return rentalRepository.findByStaffId(staffId).stream()
                .map(this::toDTO)
                .toList();
    }

    private Rental findRental(Integer id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", id));
    }

    private void validateReferences(RentalDTO rentalDTO) {
        ensureInventoryExists(rentalDTO.getInventoryId());
        ensureCustomerExists(rentalDTO.getCustomerId());
        ensureStaffExists(rentalDTO.getStaffId());
    }

    private void ensureInventoryExists(Integer inventoryId) {
        if (!inventoryRepository.existsById(inventoryId)) {
            throw new ResourceNotFoundException("Inventory", "id", inventoryId);
        }
    }

    private void ensureCustomerExists(Short customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer", "id", customerId);
        }
    }

    private void ensureStaffExists(Integer staffId) {
        if (!staffRepository.existsById(staffId)) {
            throw new ResourceNotFoundException("Staff", "id", staffId);
        }
    }

    private RentalDTO toDTO(Rental rental) {
        return new RentalDTO(
                rental.getRentalId(),
                rental.getRentalDate(),
                rental.getInventoryId(),
                rental.getCustomerId(),
                rental.getReturnDate(),
                rental.getStaffId(),
                rental.getLastUpdate()
        );
    }

    private Rental toEntity(RentalDTO rentalDTO) {
        Rental rental = new Rental();
        rental.setRentalId(rentalDTO.getRentalId());
        rental.setRentalDate(rentalDTO.getRentalDate());
        rental.setInventoryId(rentalDTO.getInventoryId());
        rental.setCustomerId(rentalDTO.getCustomerId());
        rental.setReturnDate(rentalDTO.getReturnDate());
        rental.setStaffId(rentalDTO.getStaffId());
        rental.setLastUpdate(rentalDTO.getLastUpdate());
        return rental;
    }
}
