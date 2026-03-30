package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.dto.staff.*;
import com.iem.FilmRentalStore.entity.*;
import com.iem.FilmRentalStore.mapper.StaffMapper;
import com.iem.FilmRentalStore.repository.*;
import com.iem.FilmRentalStore.service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    // 🔥 SAME LOGIC AS CUSTOMER
    @Transactional
    private City getOrCreateCity(AddressRequestDTO request) {

        String countryName = request.getCity().getCountry().getCountry();
        String cityName = request.getCity().getCity();

        Country country = countryRepository
                .findByCountryIgnoreCase(countryName)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setCountry(countryName);
                    return countryRepository.save(newCountry);
                });

        return cityRepository
                .findByCityIgnoreCaseAndCountry(cityName, country)
                .orElseGet(() -> {
                    City newCity = new City();
                    newCity.setCity(cityName);
                    newCity.setCountry(country);
                    return cityRepository.save(newCity);
                });
    }

    // ================= CREATE =================
    @Transactional
    @Override
    public StaffResponseDTO createStaff(StaffRequestDTO request) {

        if (staffRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (request.getEmail() != null &&
                staffRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        City city = getOrCreateCity(request.getAddress());

        Address address = addressRepository
                .findByAddressAndCity(request.getAddress().getAddress(), city)
                .orElseGet(() -> {
                    Address a = new Address();
                    a.setAddress(request.getAddress().getAddress());
                    a.setAddress2(request.getAddress().getAddress2());
                    a.setDistrict(request.getAddress().getDistrict());
                    a.setPostalCode(request.getAddress().getPostalCode());
                    a.setPhone(request.getAddress().getPhone());
                    a.setCity(city);
                    return addressRepository.save(a);
                });

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));

        Staff staff = StaffMapper.toEntity(request, address, store);

        // simple password (no encoding)
        staff.setPassword(request.getPassword());

        Staff saved = staffRepository.save(staff);

        if (Boolean.TRUE.equals(request.getIsManager())) {
            store.setManagerStaff(saved);
            storeRepository.save(store);
        }

        Staff fullStaff = staffRepository.findByStaffId(saved.getStaffId())
                .orElseThrow(() -> new EntityNotFoundException("Staff not found"));

        return StaffMapper.toResponseDTO(saved);
    }

    // ================= GET =================
    @Transactional(readOnly = true)
    @Override
    public StaffResponseDTO getStaffById(Short id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found"));

        return StaffMapper.toResponseDTO(staff);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StaffResponseDTO> getAllStaff(Pageable pageable) {
        return staffRepository.findAll(pageable)
                .map(StaffMapper::toResponseDTO);
    }


    // ================= SEARCH =================
    @Override
    @Transactional(readOnly = true)
    public Page<StaffResponseDTO> searchStaff(String name, Short storeId, Pageable pageable) {

        if (name != null) {
            return staffRepository
                    .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name, pageable)
                    .map(StaffMapper::toResponseDTO);
        }

        if (storeId != null) {
            return staffRepository
                    .findByStore_StoreId(storeId, pageable)
                    .map(StaffMapper::toResponseDTO);
        }

        return getAllStaff(pageable);
    }

    // ================= CITY =================
    @Override
    @Transactional(readOnly = true)
    public Page<StaffResponseDTO> getStaffByCity(String city, Pageable pageable) {
        return staffRepository
                .findByAddress_City_CityContainingIgnoreCase(city, pageable)
                .map(StaffMapper::toResponseDTO);
    }

    // ================= COUNTRY =================
    @Override
    @Transactional(readOnly = true)
    public Page<StaffResponseDTO> getStaffByCountry(String country, Pageable pageable) {
        return staffRepository
                .findByAddress_City_Country_CountryContainingIgnoreCase(country, pageable)
                .map(StaffMapper::toResponseDTO);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public StaffResponseDTO updateStaff(Short id, StaffRequestDTO request) {

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found"));

        staff.setFirstName(request.getFirstName());
        staff.setLastName(request.getLastName());
        staff.setEmail(request.getEmail());
        staff.setUsername(request.getUsername());
        staff.setActive(request.getActive());

        if (request.getPassword() != null) {
            staff.setPassword(request.getPassword());
        }

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));

        staff.setStore(store);

        // 🔥 ADDRESS LOGIC (SAME AS CUSTOMER)
        City city = getOrCreateCity(request.getAddress());

        Address address = addressRepository
                .findByAddressAndCity(request.getAddress().getAddress(), city)
                .orElseGet(() -> {
                    Address a = new Address();
                    a.setAddress(request.getAddress().getAddress());
                    a.setAddress2(request.getAddress().getAddress2());
                    a.setDistrict(request.getAddress().getDistrict());
                    a.setPostalCode(request.getAddress().getPostalCode());
                    a.setPhone(request.getAddress().getPhone());
                    a.setCity(city);
                    return addressRepository.save(a);
                });

        staff.setAddress(address);

        Staff saved = staffRepository.save(staff);

        if (Boolean.TRUE.equals(request.getIsManager())) {
            store.setManagerStaff(saved);
            storeRepository.save(store);
        }

        return StaffMapper.toResponseDTO(saved);
    }

    // ================= PATCH =================
    @Override
    @Transactional
    public StaffResponseDTO patchStaff(Short id, StaffPatchDTO dto) {

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found"));

        if (dto.getFirstName() != null)
            staff.setFirstName(dto.getFirstName());

        if (dto.getLastName() != null)
            staff.setLastName(dto.getLastName());

        if (dto.getEmail() != null)
            staff.setEmail(dto.getEmail());

        if (dto.getUsername() != null)
            staff.setUsername(dto.getUsername());

        if (dto.getActive() != null)
            staff.setActive(dto.getActive());

        if (dto.getPassword() != null)
            staff.setPassword(dto.getPassword());

        if (dto.getStoreId() != null) {
            Store store = storeRepository.findById(dto.getStoreId())
                    .orElseThrow(() -> new EntityNotFoundException("Store not found"));
            staff.setStore(store);
        }

        if (dto.getAddress() != null) {
            City city = getOrCreateCity(dto.getAddress());

            Address address = addressRepository
                    .findByAddressAndCity(dto.getAddress().getAddress(), city)
                    .orElseGet(() -> {
                        Address a = new Address();
                        a.setAddress(dto.getAddress().getAddress());
                        a.setAddress2(dto.getAddress().getAddress2());
                        a.setDistrict(dto.getAddress().getDistrict());
                        a.setPostalCode(dto.getAddress().getPostalCode());
                        a.setPhone(dto.getAddress().getPhone());
                        a.setCity(city);
                        return addressRepository.save(a);
                    });

            staff.setAddress(address);
        }

        Staff saved = staffRepository.save(staff);

        return StaffMapper.toResponseDTO(saved);
    }
}