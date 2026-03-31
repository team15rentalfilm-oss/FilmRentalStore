package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityPatchDTO;
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
import com.iem.FilmRentalStore.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;
    private final CountryService countryService;

    @Override
    public CityDTO createCity(CityRequestDTO request) {

        String cityName = normalize(request.getCity());
        String countryName = normalize(request.getCountry().getCountry());

        Country country = countryService.getOrCreateCountry(countryName);

        if (cityRepository.findByCityIgnoreCaseAndCountry(cityName, country).isPresent()) {
            throw new IllegalArgumentException("City already exists in this country");
        }

        City city = new City();
        city.setCity(cityName);
        city.setCountry(country);

        City saved = cityRepository.save(city);

        return CityMapper.toDTO(saved);
    }


    @Override
    public CityResponseDTO getCityById(Short id) {
        City city = cityRepository.findByIdWithCountry(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found"));

        return cityMapper.toResponseDTO(city);
    }


    @Override
    @Transactional
    public Page<CityResponseDTO> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable)
                .map(cityMapper::toResponseDTO);
    }

    @Override
    @Transactional
    public Page<CityResponseDTO> searchCitiesByName(String city, Pageable pageable) {
        return cityRepository.findByCityContainingIgnoreCase(city, pageable)
                .map(cityMapper::toResponseDTO);
    }

    @Override
    @Transactional
    public Page<CityResponseDTO> searchCitiesByCountry(String country, Pageable pageable) {
        return cityRepository.findByCountry_CountryContainingIgnoreCase(country, pageable)
                .map(cityMapper::toResponseDTO);
    }


    @Override
    public CityResponseDTO updateCity(Short id, CityRequestDTO request) {

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));

        String countryName = normalize(request.getCountry().getCountry());

        Country country = countryService.getOrCreateCountry(countryName);

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
    public CityResponseDTO patchCity(Short id, CityPatchDTO request) {

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));

        if (request.getCity() != null && !request.getCity().trim().isEmpty()) {
            city.setCity(request.getCity().trim());
        }

        if (request.getCountry() != null && !request.getCountry().trim().isEmpty()) {
            String countryName = normalize(request.getCountry());

            Country country = countryService.getOrCreateCountry(countryName);
            city.setCountry(country);
        }

        City updated = cityRepository.save(city);

        City fetched = cityRepository.findByIdWithCountry(updated.getCityId())
                .orElseThrow(() -> new EntityNotFoundException("City not found"));

        return cityMapper.toResponseDTO(fetched);
    }

    @Override
    public City getOrCreateCity(String cityName, String countryName) {

        String normalizedCity = normalize(cityName);
        String normalizedCountry = normalize(countryName);

        // 🔗 Get or create country first
        Country country = countryService.getOrCreateCountry(normalizedCountry);

        // 🔍 Check if city already exists in that country
        return cityRepository
                .findByCityIgnoreCaseAndCountry(normalizedCity, country)
                .orElseGet(() -> {
                    City newCity = new City();
                    newCity.setCity(normalizedCity);
                    newCity.setCountry(country);
                    return cityRepository.save(newCity);
                });
    }

}