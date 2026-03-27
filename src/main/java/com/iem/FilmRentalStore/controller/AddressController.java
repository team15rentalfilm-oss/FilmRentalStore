package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.AddressDTO;
import com.iem.FilmRentalStore.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;

    @PostMapping
    public AddressDTO.Response create(@RequestBody AddressDTO.Request dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<AddressDTO.Response> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AddressDTO.Response getById(@PathVariable Short id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public AddressDTO.Response update(@PathVariable Short id,
                                      @RequestBody AddressDTO.Request dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Short id) {
        service.delete(id);
        return "Address deleted successfully";
    }

    @GetMapping("/by-city/{cityId}")
    public List<AddressDTO.Response> getByCity(@PathVariable Short cityId) {
        return service.getByCity(cityId);
    }
}