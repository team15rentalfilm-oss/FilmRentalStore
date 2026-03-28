package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    // ✅ CREATE
    @PostMapping
    public AddressDTO createAddress(@Valid @RequestBody AddressRequestDTO request) {
        return addressService.createAddress(request);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public AddressDTO getAddressById(@PathVariable Short id) {
        return addressService.getAddressById(id);
    }

    // ✅ GET ALL
    @GetMapping
    public List<AddressDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // ✅ UPDATE (FULL)
    @PutMapping("/{id}")
    public AddressDTO updateAddress(@PathVariable Short id,
                                    @Valid @RequestBody AddressRequestDTO request) {
        return addressService.updateAddress(id, request);
    }

    // 🔥 PATCH (PARTIAL UPDATE)
    @PatchMapping("/{id}")
    public AddressDTO patchAddress(@PathVariable Short id,
                                   @RequestBody AddressRequestDTO request) {
        return addressService.patchAddress(id, request);
    }

    // 🔥 GET BY COUNTRY
    @GetMapping("/country")
    public List<AddressDTO> getByCountry(@RequestParam String name) {
        return addressService.getByCountry(name);
    }

    // ✅ SEARCH BY ADDRESS
    @GetMapping("/search/address")
    public List<AddressDTO> searchByAddress(@RequestParam String value) {
        return addressService.searchByAddress(value);
    }

    // ✅ SEARCH BY DISTRICT
    @GetMapping("/search/district")
    public List<AddressDTO> searchByDistrict(@RequestParam String value) {
        return addressService.searchByDistrict(value);
    }

    // ✅ SEARCH BY CITY
    @GetMapping("/search/city")
    public List<AddressDTO> searchByCity(@RequestParam String value) {
        return addressService.searchByCity(value);
    }
}