package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import com.iem.FilmRentalStore.dto.city.CityResponseDTO;
import com.iem.FilmRentalStore.dto.country.CountryResponseDTO;
import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.entity.Country;
import com.iem.FilmRentalStore.mapper.CityMapper;
import com.iem.FilmRentalStore.mapper.CountryMapper;
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
    private final CountryMapper countryMapper;


    @Override
    public CityDTO createCity(CityRequestDTO request) {

        String countryName = normalize(request.getCountry().getCountry());
        // 🔍 Find existing or create new
        Country country = countryRepository
                .findByCountryIgnoreCase(countryName)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setCountry(countryName);
                    return countryRepository.save(newCountry);
                });

        City city = cityMapper.toEntity(request);
        city.setCountry(country);

        City saved = cityRepository.save(city);

        return cityMapper.toDTO(saved);
    }


    @Override
    public CityResponseDTO getCityById(Short id) {
        City city = cityRepository.findByIdWithCountry(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found"));

        return cityMapper.toResponseDTO(city);
    }

    @Override
    public List<CityResponseDTO> getAllCities() {
        return cityRepository.findAllWithCountry()
                .stream()
                .map(cityMapper::toResponseDTO)
                .toList();
    }


    @Override
    public CityResponseDTO updateCity(Short id, CityRequestDTO request) {

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));

        String countryName = normalize(request.getCountry().getCountry());
        Country country = countryRepository
                .findByCountryIgnoreCase(countryName)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setCountry(countryName);
                    return countryRepository.save(newCountry);
                });

        city.setCity(request.getCity());
        city.setCountry(country);

        City updated = cityRepository.save(city);
        City fetched = cityRepository.findByIdWithCountry(updated.getCityId())
                .orElseThrow(() -> new EntityNotFoundException("City not found"));

        return cityMapper.toResponseDTO(fetched);
    }

    @Override
    public List<CountryResponseDTO> searchCountries(String name) {
        return countryRepository.findByCountryContainingIgnoreCase(name)
                .stream()
                .map(CountryMapper::toResponseDTO)
                .toList();
    }

    private String normalize(String input) {
        return input == null ? null : input.trim();
    }

    @Override
    public List<CityResponseDTO> searchCitiesByName(String city) {
        return cityRepository.findByCityWithCountry(city)
                .stream()
                .map(cityMapper::toResponseDTO)
                .toList();
    }
    @Override
    public List<CityResponseDTO> searchCitiesByCountry(String country) {
        return cityRepository.findByCountryNameWithCountry(country)
                .stream()
                .map(cityMapper::toResponseDTO)
                .toList();
    }

}