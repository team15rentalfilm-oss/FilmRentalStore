package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.CountryDTO;
import com.iem.FilmRentalStore.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService service;

    // 🔹 CREATE
    @PostMapping
    public CountryDTO.Response create(@RequestBody CountryDTO.Request dto) {
        return service.create(dto);
    }

    // 🔹 GET ALL
    @GetMapping
    public List<CountryDTO.Response> getAll() {
        return service.getAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public CountryDTO.Response getById(@PathVariable Short id) {
        return service.getById(id);
    }

    // 🔹 UPDATE
    @PutMapping("/{id}")
    public CountryDTO.Response update(@PathVariable Short id,
                                      @RequestBody CountryDTO.Request dto) {
        return service.update(id, dto);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Short id) {
        service.delete(id);
        return "Country deleted successfully";
    }
}