package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.CityDTO;
import com.iem.FilmRentalStore.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService service;

    @PostMapping
    public CityDTO.Response create(@Valid @RequestBody CityDTO.Request dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CityDTO.Response> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CityDTO.Response getById(@PathVariable Short id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public CityDTO.Response update(@PathVariable Short id,
                                   @Valid @RequestBody CityDTO.Request dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Short id) {
        service.delete(id);
        return "City deleted successfully";
    }

    @GetMapping("/by-country/{countryId}")
    public List<CityDTO.Response> getByCountry(@PathVariable Short countryId) {
        return service.getByCountry(countryId);
    }
}
