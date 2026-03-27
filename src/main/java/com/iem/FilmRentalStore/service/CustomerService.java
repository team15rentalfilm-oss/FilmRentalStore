package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.customer.CustomerDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerRequestDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerRequestDTO request);

    CustomerDTO getCustomerById(Short id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(Short id, CustomerRequestDTO request);
}