package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.entity.Country;
import com.iem.FilmRentalStore.mapper.AddressMapper;
import com.iem.FilmRentalStore.repository.AddressRepository;
import com.iem.FilmRentalStore.repository.CityRepository;
import com.iem.FilmRentalStore.repository.CountryRepository;
import com.iem.FilmRentalStore.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    // 🔥 COMMON METHOD (VERY IMPORTANT)
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
    public AddressDTO createAddress(AddressRequestDTO request) {

        City city = getOrCreateCity(request);

        Address address = AddressMapper.toEntity(request);
        address.setCity(city);

        Address saved = addressRepository.save(address);

        return AddressMapper.toDTO(saved);
    }

    // ✅ GET BY ID
    @Override
    public AddressDTO getAddressById(Short id) {
        Address address = addressRepository.findByIdWithCityAndCountry(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));

        return AddressMapper.toDTO(address);
    }

    // ✅ GET ALL
    @Override
    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    // ✅ UPDATE (FULL)
    @Override
    public AddressDTO updateAddress(Short id, AddressRequestDTO request) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));

        City city = getOrCreateCity(request);

        address.setAddress(request.getAddress());
        address.setAddress2(request.getAddress2());
        address.setDistrict(request.getDistrict());
        address.setPostalCode(request.getPostalCode());
        address.setPhone(request.getPhone());
        address.setCity(city);

        Address updated = addressRepository.save(address);

        return AddressMapper.toDTO(updated);
    }

    // 🔥 FIXED (IMPORTANT)
    @Override
    public Address createAndReturnEntity(AddressRequestDTO request) {

        City city = getOrCreateCity(request);

        Address address = AddressMapper.toEntity(request);
        address.setCity(city);

        return addressRepository.save(address);
    }

    // ✅ GET BY COUNTRY
    @Override
    public List<AddressDTO> getByCountry(String country) {
        return addressRepository
                .findByCity_Country_CountryContainingIgnoreCase(country)
                .stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    // 🔥 PATCH (PARTIAL UPDATE)
    @Override
    public AddressDTO patchAddress(Short id, AddressRequestDTO request) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));

        if (request.getAddress() != null)
            address.setAddress(request.getAddress());

        if (request.getAddress2() != null)
            address.setAddress2(request.getAddress2());

        if (request.getDistrict() != null)
            address.setDistrict(request.getDistrict());

        if (request.getPostalCode() != null)
            address.setPostalCode(request.getPostalCode());

        if (request.getPhone() != null)
            address.setPhone(request.getPhone());

        // 🔥 Only update city if provided
        if (request.getCity() != null && request.getCity().getCountry() != null) {
            City city = getOrCreateCity(request);
            address.setCity(city);
        }

        Address updated = addressRepository.save(address);

        return AddressMapper.toDTO(updated);
    }

    @Override
    public List<AddressDTO> searchByAddress(String address) {
        return addressRepository.findByAddressContainingIgnoreCase(address)
                .stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    @Override
    public List<AddressDTO> searchByDistrict(String district) {
        return addressRepository.findByDistrictContainingIgnoreCase(district)
                .stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    @Override
    public List<AddressDTO> searchByCity(String city) {
        return addressRepository.findByCity_CityContainingIgnoreCase(city)
                .stream()
                .map(AddressMapper::toDTO)
                .toList();
    }
}