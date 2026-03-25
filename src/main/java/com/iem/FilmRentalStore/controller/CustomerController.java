package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.entity.Customer;
import com.iem.FilmRentalStore.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable short id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<Customer> search(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Byte storeId
    ) {
        return service.search(firstName, lastName, email, storeId);
    }

    @PostMapping
    public Customer create(@RequestBody Customer c) {
        return service.create(c);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable short id, @RequestBody Customer c) {
        return service.update(id, c);
    }

    @PatchMapping("/{id}")
    public Customer patch(@PathVariable short id, @RequestBody Customer c) {
        return service.patch(id, c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable short id) {
        service.delete(id);
    }
}
