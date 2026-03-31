package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.store.StoreDTO;
import com.iem.FilmRentalStore.dto.store.StoreRequestDTO;
import com.iem.FilmRentalStore.dto.store.StoreResponseDTO;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.Store;
import com.iem.FilmRentalStore.entity.Staff;
import com.iem.FilmRentalStore.mapper.StoreMapper;
import com.iem.FilmRentalStore.repository.StoreRepository;
import com.iem.FilmRentalStore.repository.StaffRepository;
import com.iem.FilmRentalStore.service.AddressService;
import com.iem.FilmRentalStore.service.StoreService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StaffRepository staffRepository;
    private final AddressService addressService;

    @Override
    @Transactional
    public StoreResponseDTO createStore(StoreRequestDTO request) {

        // ✅ Step 1: Resolve Address
        Address address = addressService.createAndReturnEntity(request.getAddress());

        // ✅ Step 2: Fetch Manager (MANDATORY)
        Staff manager = staffRepository.findById(request.getManagerStaffId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Staff not found with id: " + request.getManagerStaffId()));

        // ✅ Step 3: Create Store properly
        Store store = new Store();
        store.setAddress(address);
        store.setManagerStaff(manager); // 🔥 IMPORTANT

        // ✅ Step 4: Save
        Store saved = storeRepository.save(store);

        return StoreMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional
    public StoreResponseDTO getStoreById(Short id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + id));

        return StoreMapper.toResponseDTO(store);
    }

    @Override
    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll()
                .stream()
                .map(StoreMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public StoreResponseDTO updateStore(Short id, StoreRequestDTO request) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + id));

        // 🔥 Update Address if provided
        if (request.getAddress() != null) {
            Address address = addressService.createAndReturnEntity(request.getAddress());
            store.setAddress(address);
        }

        // 🔥 Set manager (after store exists)
        if (request.getManagerStaffId() != null) {
            Staff manager = staffRepository.findById(request.getManagerStaffId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Staff not found with id: " + request.getManagerStaffId()));

            store.setManagerStaff(manager);
        }

        Store updated = storeRepository.save(store);

        return StoreMapper.toResponseDTO(updated);
    }
}