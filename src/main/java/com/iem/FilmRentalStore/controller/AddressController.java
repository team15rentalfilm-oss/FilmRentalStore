package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.dto.address.AddressResponseDTO;
import com.iem.FilmRentalStore.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public AddressResponseDTO getAddressById(@PathVariable Short id) {
        return addressService.getAddressById(id);
    }

    @GetMapping
    public Page<AddressResponseDTO> getAllAddresses(
            @PageableDefault(size = 10, sort = "addressId") Pageable pageable) {
        return addressService.getAllAddresses(pageable);
    }

    @PutMapping("/{id}")
    public AddressDTO updateAddress(@PathVariable Short id,
                                    @Valid @RequestBody AddressRequestDTO request) {
        return addressService.updateAddress(id, request);
    }

    @PatchMapping("/{id}")
    public AddressDTO patchAddress(@PathVariable Short id,
                                   @RequestBody AddressRequestDTO request) {
        return addressService.patchAddress(id, request);
    }

    @GetMapping("/country")
    public Page<AddressResponseDTO> getByCountry(
            @RequestParam String name,
            @PageableDefault(size = 10) Pageable pageable) {
        return addressService.getByCountry(name, pageable);
    }

    @GetMapping("/search/address")
    public Page<AddressResponseDTO> searchByAddress(
            @RequestParam String value,
            @PageableDefault(size = 10) Pageable pageable) {
        return addressService.searchByAddress(value, pageable);
    }

    @GetMapping("/search/district")
    public Page<AddressResponseDTO> searchByDistrict(
            @RequestParam String value,
            @PageableDefault(size = 10) Pageable pageable) {
        return addressService.searchByDistrict(value, pageable);
    }

    @GetMapping("/search/city")
    public Page<AddressResponseDTO> searchByCity(
            @RequestParam String value,
            @PageableDefault(size = 10) Pageable pageable) {
        return addressService.searchByCity(value, pageable);
    }
}
