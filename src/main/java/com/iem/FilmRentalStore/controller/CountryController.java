package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.country.CountryDTO;
import com.iem.FilmRentalStore.dto.country.CountryRequestDTO;
import com.iem.FilmRentalStore.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    public CountryDTO createCountry(@Valid @RequestBody CountryRequestDTO request) {
        return countryService.createCountry(request);
    }

    @GetMapping("/{id}")
    public CountryDTO getCountryById(@PathVariable Short id) {
        return countryService.getCountryById(id);
    }

    @GetMapping
    public List<CountryDTO> getAllCountries() {
        return countryService.getAllCountries();
    }

    @PutMapping("/{id}")
    public CountryDTO updateCountry(@PathVariable Short id,
                                    @Valid @RequestBody CountryRequestDTO request) {
        return countryService.updateCountry(id, request);
    }
}