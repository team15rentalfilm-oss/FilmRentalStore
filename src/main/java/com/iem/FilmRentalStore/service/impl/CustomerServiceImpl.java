package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.entity.Customer;
import com.iem.FilmRentalStore.repository.CustomerRepository;
import com.iem.FilmRentalStore.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Customer> getAll() {
        return repo.findAll();
    }

    @Override
    public Customer getById(Short id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public List<Customer> search(String firstName, String lastName, String email, Byte storeId) {
        if (firstName != null && !firstName.isBlank()) {
            return repo.findByFirstNameContainingIgnoreCase(firstName);
        }
        if (lastName != null && !lastName.isBlank()) {
            return repo.findByLastNameContainingIgnoreCase(lastName);
        }
        if (email != null && !email.isBlank()) {
            return repo.findByEmailContainingIgnoreCase(email);
        }
        if (storeId != null) {
            return repo.findByStore_StoreId(storeId);
        }
        return repo.findAll();
    }

    @Override
    public Customer create(Customer c) {
        return repo.save(c);
    }

    @Override
    public Customer update(Short id, Customer c) {
        Customer existing = getById(id);

        existing.setStore(c.getStoreId());
        existing.setFirstName(c.getFirstName());
        existing.setLastName(c.getLastName());
        existing.setEmail(c.getEmail());
        existing.setAddressId(c.getAddressId());
        existing.setActive(c.getActive());

        return repo.save(existing);
    }

    @Override
    public Customer patch(Short id, Customer c) {
        Customer existing = getById(id);

        if (c.getStoreId() != null) existing.setStoreId(c.getStoreId());
        if (c.getFirstName() != null) existing.setFirstName(c.getFirstName());
        if (c.getLastName() != null) existing.setLastName(c.getLastName());
        if (c.getEmail() != null) existing.setEmail(c.getEmail());
        if (c.getAddressId() != null) existing.setAddressId(c.getAddressId());
        if (c.getActive() != null) existing.setActive(c.getActive());

        return repo.save(existing);
    }

    @Override
    public void delete(Short id) {
        repo.deleteById(id);
    }
}
