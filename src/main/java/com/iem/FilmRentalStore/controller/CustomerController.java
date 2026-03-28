package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.customer.CustomerDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerRequestDTO;
import com.iem.FilmRentalStore.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerRequestDTO request) {
        return customerService.createCustomer(request);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Short id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Short id,
                                      @Valid @RequestBody CustomerRequestDTO request) {
        return customerService.updateCustomer(id, request);
    }
}