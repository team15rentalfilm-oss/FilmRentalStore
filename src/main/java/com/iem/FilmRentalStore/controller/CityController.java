package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import com.iem.FilmRentalStore.service.CityService;
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

    @GetMapping("/{id}")
    public CityDTO getCityById(@PathVariable Short id) {
        return cityService.getCityById(id);
    }

    @GetMapping
    public List<CityDTO> getAllCities() {
        return cityService.getAllCities();
    }

    @PutMapping("/{id}")
    public CityDTO updateCity(@PathVariable Short id,
                              @Valid @RequestBody CityRequestDTO request) {
        return cityService.updateCity(id, request);
    }
}