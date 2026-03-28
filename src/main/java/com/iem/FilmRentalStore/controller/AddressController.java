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

    @PostMapping
    public AddressDTO createAddress(@Valid @RequestBody AddressRequestDTO request) {
        return addressService.createAddress(request);
    }

    @GetMapping("/{id}")
    public AddressDTO getAddressById(@PathVariable Short id) {
        return addressService.getAddressById(id);
    }

    @GetMapping
    public List<AddressDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PutMapping("/{id}")
    public AddressDTO updateAddress(@PathVariable Short id,
                                    @Valid @RequestBody AddressRequestDTO request) {
        return addressService.updateAddress(id, request);
    }
}