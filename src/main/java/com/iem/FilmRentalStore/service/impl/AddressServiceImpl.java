package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.dto.address.AddressResponseDTO;
import com.iem.FilmRentalStore.entity.*;
import com.iem.FilmRentalStore.mapper.AddressMapper;
import com.iem.FilmRentalStore.repository.*;
import com.iem.FilmRentalStore.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    // 🔥 COMMON
    private City getOrCreateCity(AddressRequestDTO request) {

        if (request.getCity() == null || request.getCity().getCountry() == null) {
            throw new IllegalArgumentException("City and Country must be provided");
        }

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

    // ✅ CREATE
    @Override
    @Transactional
    public AddressDTO createAddress(AddressRequestDTO request) {

        City city = getOrCreateCity(request);

        Address address = AddressMapper.toEntity(request);
        address.setCity(city);

        Address saved = addressRepository.save(address);

        Address full = addressRepository.findByIdWithFetch(saved.getAddressId())
                .orElseThrow();

        return AddressMapper.toDTO(full);
    }

    // ✅ GET BY ID
    @Override
    @Transactional
    public AddressResponseDTO getAddressById(Short id) {
        return addressRepository.findByIdWithFetch(id)
                .map(AddressMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }

    // ✅ PAGINATION
    @Override
    @Transactional
    public Page<AddressResponseDTO> getAllAddresses(Pageable pageable) {
        return addressRepository.findAllWithFetch(pageable)
                .map(AddressMapper::toResponseDTO);
    }

    // ✅ UPDATE
    @Override
    @Transactional
    public AddressDTO updateAddress(Short id, AddressRequestDTO request) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        City city = getOrCreateCity(request);

        address.setAddress(request.getAddress());
        address.setAddress2(request.getAddress2());
        address.setDistrict(request.getDistrict());
        address.setPostalCode(request.getPostalCode());
        address.setPhone(request.getPhone());
        address.setCity(city);

        Address updated = addressRepository.save(address);

        return addressRepository.findByIdWithFetch(updated.getAddressId())
                .map(AddressMapper::toDTO)
                .orElseThrow();
    }

    // 🔥 PATCH
    @Override
    @Transactional
    public AddressDTO patchAddress(Short id, AddressRequestDTO request) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        if (request.getAddress() != null) address.setAddress(request.getAddress());
        if (request.getAddress2() != null) address.setAddress2(request.getAddress2());
        if (request.getDistrict() != null) address.setDistrict(request.getDistrict());
        if (request.getPostalCode() != null) address.setPostalCode(request.getPostalCode());
        if (request.getPhone() != null) address.setPhone(request.getPhone());

        if (request.getCity() != null && request.getCity().getCountry() != null) {
            address.setCity(getOrCreateCity(request));
        }

        Address updated = addressRepository.save(address);

        return addressRepository.findByIdWithFetch(updated.getAddressId())
                .map(AddressMapper::toDTO)
                .orElseThrow();
    }

    // ✅ SEARCH
    @Override
    @Transactional
    public Page<AddressResponseDTO> searchByAddress(String address, Pageable pageable) {
        return addressRepository.findByAddressWithFetch(address, pageable)
                .map(AddressMapper::toResponseDTO);
    }

    @Override
    @Transactional
    public Page<AddressResponseDTO> searchByDistrict(String district, Pageable pageable) {
        return addressRepository.findByDistrictWithFetch(district, pageable)
                .map(AddressMapper::toResponseDTO);
    }

    @Override
    @Transactional
    public Page<AddressResponseDTO> searchByCity(String city, Pageable pageable) {
        return addressRepository.findByCityWithFetch(city, pageable)
                .map(AddressMapper::toResponseDTO);
    }

    @Override
    @Transactional
    public Page<AddressResponseDTO> getByCountry(String country, Pageable pageable) {
        return addressRepository.findByCountryWithFetch(country, pageable)
                .map(AddressMapper::toResponseDTO);
    }

    // ✅ REUSE
    @Override
    @Transactional
    public Address createAndReturnEntity(AddressRequestDTO request) {
        Address address = AddressMapper.toEntity(request);
        address.setCity(getOrCreateCity(request));
        return addressRepository.save(address);
    }
}