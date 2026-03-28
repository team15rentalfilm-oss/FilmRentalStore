package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.customer.CustomerDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerPatchDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerRequestDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerResponseDTO;
import com.iem.FilmRentalStore.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // ✅ CREATE
    @PostMapping
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerRequestDTO request) {
        return customerService.createCustomer(request);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable Short id) {
        return customerService.getCustomerById(id);
    }

    // ✅ GET ALL
    @GetMapping
    public List<CustomerDTO> getCustomers() {
        return customerService.getAllCustomers();
    }

    // ✅ UPDATE (FULL)
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Short id,
                                      @Valid @RequestBody CustomerRequestDTO request) {
        return customerService.updateCustomer(id, request);
    }

    // ================= SEARCH APIs =================

    // 🔍 By First Name
    @GetMapping("/search/first-name")
    public List<CustomerDTO> getByFirstName(@RequestParam String firstName) {
        return customerService.getByFirstName(firstName);
    }

    // 🔍 By Last Name
    @GetMapping("/search/last-name")
    public List<CustomerDTO> getByLastName(@RequestParam String lastName) {
        return customerService.getByLastName(lastName);
    }

    // 🔍 By Email
    @GetMapping("/search/email")
    public List<CustomerDTO> getByEmail(@RequestParam String email) {
        return customerService.getByEmail(email);
    }

    // 🔍 By Active Status
    @GetMapping("/search/active")
    public List<CustomerDTO> getByActive(@RequestParam Boolean active) {
        return customerService.getByActive(active);
    }

    // 🔍 By City
    @GetMapping("/search/city")
    public List<CustomerDTO> getByCity(@RequestParam String city) {
        return customerService.getByCity(city);
    }

    // 🔍 By Country
    @GetMapping("/search/country")
    public List<CustomerDTO> getByCountry(@RequestParam String country) {
        return customerService.getByCountry(country);
    }

    // ================= PATCH =================

    // 🔧 PARTIAL UPDATE
    // 🔧 PARTIAL UPDATE
    @PatchMapping("/{id}")
    public CustomerDTO patchCustomer(@PathVariable Short id,
                                     @RequestBody CustomerPatchDTO dto) {
        return customerService.patchCustomer(id, dto);
    }
}