package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import com.iem.FilmRentalStore.dto.city.CityResponseDTO;
import com.iem.FilmRentalStore.dto.country.CountryResponseDTO;
import com.iem.FilmRentalStore.service.CityService;
import com.iem.FilmRentalStore.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public CityDTO createCity(@Valid @RequestBody CityRequestDTO request) {
        return cityService.createCity(request);
    }

    @PutMapping("/{id}")
    public CityResponseDTO updateCity(@PathVariable Short id,
                              @Valid @RequestBody CityRequestDTO request) {
        return cityService.updateCity(id, request);
    }

    @GetMapping("/{id}")
    public CityResponseDTO getCityById(@PathVariable Short id) {
        return cityService.getCityById(id);
    }

    @GetMapping
    public List<CityResponseDTO> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/search/city")
    public List<CityResponseDTO> searchCities(@RequestParam String city) {
        return cityService.searchCitiesByName(city);
    }

    @GetMapping("/search/country")
    public List<CityResponseDTO> searchCitiesByCountry(@RequestParam String country) {
        return cityService.searchCitiesByCountry(country);
    }
}