package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAll();

    Customer getById(Short id);

    List<Customer> search(String firstName, String lastName, String email, Short storeId);

    Customer create(Customer c);

    Customer update(Short id, Customer c);

    Customer patch(Short id, Customer c);

    void delete(Short id);
}