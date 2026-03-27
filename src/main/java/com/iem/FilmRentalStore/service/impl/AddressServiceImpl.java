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

    @Override
    public AddressDTO createAddress(AddressRequestDTO request) {

        if (request.getCity() == null || request.getCity().getCountry() == null) {
            throw new IllegalArgumentException("City and Country must be provided");
        }

        String countryName = request.getCity().getCountry().getCountry();
        String cityName = request.getCity().getCity();

        // 🔥 Step 1: Find or create country
        Country country = countryRepository
                .findByCountryIgnoreCase(countryName)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setCountry(countryName);
                    return countryRepository.save(newCountry);
                });

        // 🔥 Step 2: Find or create city
        City city = cityRepository
                .findByCityIgnoreCaseAndCountry(cityName, country)
                .orElseGet(() -> {
                    City newCity = new City();
                    newCity.setCity(cityName);
                    newCity.setCountry(country);
                    return cityRepository.save(newCity);
                });

        // Step 3: Map address
        Address address = AddressMapper.toEntity(request);

        // Step 4: Set relationship
        address.setCity(city);

        Address saved = addressRepository.save(address);

        return AddressMapper.toDTO(saved);
    }

    @Override
    public AddressDTO getAddressById(Short id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));

        return AddressMapper.toDTO(address);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(AddressMapper::toDTO)
                .toList();
    }

    @Override
    public AddressDTO updateAddress(Short id, AddressRequestDTO request) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));

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

        City city = cityRepository
                .findByCityIgnoreCaseAndCountry(cityName, country)
                .orElseGet(() -> {
                    City newCity = new City();
                    newCity.setCity(cityName);
                    newCity.setCountry(country);
                    return cityRepository.save(newCity);
                });

        // 🔥 Update fields
        address.setAddress(request.getAddress());
        address.setAddress2(request.getAddress2());
        address.setDistrict(request.getDistrict());
        address.setPostalCode(request.getPostalCode());
        address.setPhone(request.getPhone());
        address.setCity(city);

        Address updated = addressRepository.save(address);

        return AddressMapper.toDTO(updated);
    }

    @Override
    public Address createAndReturnEntity(AddressRequestDTO request) {
        return null;
    }
}