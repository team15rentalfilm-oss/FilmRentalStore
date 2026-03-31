package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.customer.*;
import com.iem.FilmRentalStore.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "customerId"
    );

    private static final String DEFAULT_SORT_FIELD = "customerId";

    @PostMapping
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerRequestDTO request) {
        return customerService.createCustomer(request);
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getCustomer(@PathVariable Short id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public Page<CustomerDTO> getCustomers(Pageable pageable) {
        return customerService.getAllCustomers(sanitize(pageable));
    }


    @GetMapping("/search/first-name")
    public Page<CustomerDTO> getByFirstName(@RequestParam String firstName,
                                            Pageable pageable) {
        return customerService.getByFirstName(firstName, sanitize(pageable));
    }

    @GetMapping("/search/last-name")
    public Page<CustomerDTO> getByLastName(@RequestParam String lastName,
                                           Pageable pageable) {
        return customerService.getByLastName(lastName, sanitize(pageable));
    }

    @GetMapping("/search/email")
    public Page<CustomerDTO> getByEmail(@RequestParam String email,
                                        Pageable pageable) {
        return customerService.getByEmail(email, sanitize(pageable));
    }

    @GetMapping("/search/active")
    public Page<CustomerDTO> getByActive(@RequestParam Boolean active,
                                         Pageable pageable) {
        return customerService.getByActive(active, sanitize(pageable));
    }

    @GetMapping("/search/city")
    public Page<CustomerDTO> getByCity(@RequestParam String city,
                                       Pageable pageable) {
        return customerService.getByCity(city, sanitize(pageable));
    }

    @GetMapping("/search/country")
    public Page<CustomerDTO> getByCountry(@RequestParam String country,
                                          Pageable pageable) {
        return customerService.getByCountry(country, sanitize(pageable));
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Short id,
                                      @Valid @RequestBody CustomerRequestDTO request) {
        return customerService.updateCustomer(id, request);
    }

    @PatchMapping("/{id}")
    public CustomerDTO patchCustomer(@PathVariable Short id,
                                     @RequestBody CustomerPatchDTO dto) {
        return customerService.patchCustomer(id, dto);
    }


    private Pageable sanitize(Pageable pageable) {

        int page = pageable.getPageNumber();
        int size = Math.min(pageable.getPageSize(), 50);

        Sort sort = pageable.getSort();

        if (!sort.isSorted()) {
            sort = Sort.by(DEFAULT_SORT_FIELD).ascending();
        } else {
            for (Sort.Order order : sort) {
                if (!ALLOWED_SORT_FIELDS.contains(order.getProperty())) {
                    throw new IllegalArgumentException(
                            "Invalid sort field: " + order.getProperty()
                    );
                }
            }
        }

        return PageRequest.of(page, size, sort);
    }
}
