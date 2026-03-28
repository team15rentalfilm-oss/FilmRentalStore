package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.entity.Country;
import com.iem.FilmRentalStore.mapper.CityMapper;
import com.iem.FilmRentalStore.repository.CityRepository;
import com.iem.FilmRentalStore.repository.CountryRepository;
import com.iem.FilmRentalStore.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;

    @Override
    public CityDTO createCity(CityRequestDTO request) {

        if (request.getCountry() == null) {
            throw new IllegalArgumentException("Country must be provided");
        }

        String countryName = request.getCountry().getCountry();

        // 🔥 Find or create country
        Country country = countryRepository
                .findByCountryIgnoreCase(countryName)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setCountry(countryName);
                    return countryRepository.save(newCountry);
                });

        // Convert DTO → Entity
        City city = cityMapper.toEntity(request);

        // Set relationship
        city.setCountry(country);

        City saved = cityRepository.save(city);

        return cityMapper.toDTO(saved);
    }

    @Override
    public CityDTO getCityById(Integer id) {
        return null;
    }

    @Override
    public CityDTO getCityById(Short id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));

        return cityMapper.toDTO(city);
    }

    @Override
    public List<CityDTO> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(CityMapper::toDTO)
                .toList();
    }

    @Override
    public CityDTO updateCity(Integer id, CityRequestDTO request) {
        return null;
    }

    @Override
    public CityDTO updateCity(Short id, CityRequestDTO request) {

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));

        if (request.getCountry() == null) {
            throw new IllegalArgumentException("Country must be provided");
        }

        String countryName = request.getCountry().getCountry();

        // Find or create country
        Country country = countryRepository
                .findByCountryIgnoreCase(countryName)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setCountry(countryName);
                    return countryRepository.save(newCountry);
                });

        // Update fields
        city.setCity(request.getCity());
        city.setCountry(country);

        City updated = cityRepository.save(city);

        return cityMapper.toDTO(updated);
    }
}