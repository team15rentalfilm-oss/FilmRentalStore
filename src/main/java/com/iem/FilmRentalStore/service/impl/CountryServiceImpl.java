package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.country.CountryDTO;
import com.iem.FilmRentalStore.dto.country.CountryRequestDTO;
import com.iem.FilmRentalStore.entity.Country;
import com.iem.FilmRentalStore.mapper.CountryMapper;
import com.iem.FilmRentalStore.repository.CountryRepository;
import com.iem.FilmRentalStore.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public CountryDTO createCountry(CountryRequestDTO request) {
        Country country = countryMapper.toEntity(request);
        Country saved = countryRepository.save(country);
        return countryMapper.toDTO(saved);
    }

    @Override
    public CountryDTO getCountryById(Integer id) {
        return null;
    }

    @Override
    public CountryDTO getCountryById(Short id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));

        return countryMapper.toDTO(country);
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(CountryMapper::toDTO)
                .toList();
    }

    @Override
    public CountryDTO updateCountry(Integer id, CountryRequestDTO request) {
        return null;
    }

    @Override
    public CountryDTO updateCountry(Short id, CountryRequestDTO request) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));

        country.setCountry(request.getCountry());

        Country updated = countryRepository.save(country);
        return countryMapper.toDTO(updated);
    }
}