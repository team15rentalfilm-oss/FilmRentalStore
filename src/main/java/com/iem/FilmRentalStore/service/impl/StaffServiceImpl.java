package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.staff.StaffDTO;
import com.iem.FilmRentalStore.dto.staff.StaffRequestDTO;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.Staff;
import com.iem.FilmRentalStore.entity.Store;
import com.iem.FilmRentalStore.mapper.StaffMapper;
import com.iem.FilmRentalStore.repository.StaffRepository;
import com.iem.FilmRentalStore.repository.StoreRepository;
import com.iem.FilmRentalStore.service.AddressService;
import com.iem.FilmRentalStore.service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StoreRepository storeRepository;
    private final AddressService addressService;

    @Override
    public StaffDTO createStaff(StaffRequestDTO request) {

        // 🔥 Step 1: Resolve Address
        Address address = addressService.createAndReturnEntity(request.getAddress());

        // 🔥 Step 2: Fetch Store
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Store not found with id: " + request.getStoreId()));

        // 🔥 Step 3: Map Staff
        Staff staff = StaffMapper.toEntity(request, address, store);

        Staff saved = staffRepository.save(staff);

        // 🔥 Step 4: OPTIONAL → set as manager
        if (request.getIsManager() != null && request.getIsManager()) {
            store.setManagerStaff(saved);
            storeRepository.save(store);
        }

        return StaffMapper.toDTO(saved);
    }

    @Override
    public StaffDTO getStaffById(Short id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found with id: " + id));

        return StaffMapper.toDTO(staff);
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(StaffMapper::toDTO)
                .toList();
    }

    @Override
    public StaffDTO updateStaff(Short id, StaffRequestDTO request) {

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found with id: " + id));

        Address address = addressService.createAndReturnEntity(request.getAddress());

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Store not found with id: " + request.getStoreId()));

        Staff updatedData = StaffMapper.toEntity(request, address, store);

        // Update fields
        staff.setFirstName(updatedData.getFirstName());
        staff.setLastName(updatedData.getLastName());
        staff.setEmail(updatedData.getEmail());
        staff.setUsername(updatedData.getUsername());
        staff.setPassword(updatedData.getPassword());
        staff.setActive(updatedData.getActive());
        staff.setAddress(updatedData.getAddress());
        staff.setStore(updatedData.getStore());

        Staff saved = staffRepository.save(staff);

        // 🔥 Update manager if needed
        if (request.getIsManager() != null && request.getIsManager()) {
            store.setManagerStaff(saved);
            storeRepository.save(store);
        }

        return StaffMapper.toDTO(saved);
    }
}