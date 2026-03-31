package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityPatchDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import com.iem.FilmRentalStore.dto.city.CityResponseDTO;
import com.iem.FilmRentalStore.dto.country.CountryResponseDTO;
import com.iem.FilmRentalStore.service.CityService;
import com.iem.FilmRentalStore.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    // ✅ CREATE
    @PostMapping
    public CityDTO createCity(@Valid @RequestBody CityRequestDTO request) {
        return cityService.createCity(request);
    }

    // ✅ UPDATE (FULL)
    @PutMapping("/{id}")
    public CityResponseDTO updateCity(@PathVariable Short id,
                                      @Valid @RequestBody CityRequestDTO request) {
        return cityService.updateCity(id, request);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public CityResponseDTO getCityById(@PathVariable Short id) {
        return cityService.getCityById(id);
    }

    // 🔥 GET ALL WITH PAGINATION
    @GetMapping
    public Page<CityResponseDTO> getAllCities(
            @PageableDefault(size = 10, sort = "cityId") Pageable pageable) {
        return cityService.getAllCities(pageable);
    }

    // 🔎 SEARCH BY CITY (PAGINATED)
    @GetMapping("/search/city")
    public Page<CityResponseDTO> searchCities(
            @RequestParam String city,
            @PageableDefault(size = 10) Pageable pageable) {
        return cityService.searchCitiesByName(city, pageable);
    }

    // 🔎 SEARCH BY COUNTRY (PAGINATED)
    @GetMapping("/search/country")
    public Page<CityResponseDTO> searchCitiesByCountry(
            @RequestParam String country,
            @PageableDefault(size = 10) Pageable pageable) {
        return cityService.searchCitiesByCountry(country, pageable);
    }

    // 🔥 PATCH
    @PatchMapping("/{id}")
    public CityResponseDTO patchCity(@PathVariable Short id,
                                     @RequestBody CityPatchDTO request) {
        return cityService.patchCity(id, request);
    }
}