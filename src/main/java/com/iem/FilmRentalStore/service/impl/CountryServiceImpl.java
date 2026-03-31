package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.country.CountryDTO;
import com.iem.FilmRentalStore.dto.country.CountryRequestDTO;
import com.iem.FilmRentalStore.dto.country.CountryResponseDTO;
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

        String normalized = normalize(request.getCountry());

        countryRepository.findByCountryIgnoreCase(normalized)
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Country already exists: " + normalized);
                });

        Country country = new Country();
        country.setCountry(normalized);

        Country saved = countryRepository.save(country);

        return countryMapper.toDTO(saved);
    }

    @Override
    public CountryResponseDTO getCountryById(Short id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));

        return countryMapper.toResponseDTO(country);
    }

    @Override
    public List<CountryResponseDTO> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(CountryMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<CountryResponseDTO> searchCountries(String name) {

        String normalized = name == null ? "" : name.trim();

        if (normalized.isEmpty()) {
            return List.of();
        }

        return countryRepository
                .findByCountryContainingIgnoreCase(normalized)
                .stream()
                .map(CountryMapper::toResponseDTO)
                .toList();
    }

    @Override
    public CountryDTO updateCountry(Short id, CountryRequestDTO request) {

        Country existing = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));

        String normalized = normalize(request.getCountry());

        countryRepository.findByCountryIgnoreCase(normalized)
                .filter(c -> !c.getCountryId().equals(id))
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Country already exists: " + normalized);
                });

        existing.setCountry(normalized);

        Country updated = countryRepository.save(existing);

        return countryMapper.toDTO(updated);
    }

    @Override
    public Country getOrCreateCountry(String countryName) {

        String normalized = normalize(countryName);

        return countryRepository
                .findByCountryIgnoreCase(normalized)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setCountry(normalized);
                    return countryRepository.save(newCountry);
                });
    }

    private String normalize(String input) {
        return input == null ? null : input.trim().replaceAll("\\s+", " ");
    }
}
