package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.customer.CustomerDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerRequestDTO;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerRequestDTO request);

    CustomerDTO getCustomerById(Short id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(Short id, CustomerRequestDTO request);

    List<CustomerDTO> getByFirstName(String firstName);
    List<CustomerDTO> getByLastName(String lastName);
    List<CustomerDTO> getByEmail(String email);
    List<CustomerDTO> getByActive(Boolean active);
    List<CustomerDTO> getByCity(String city);
    List<CustomerDTO> getByCountry(String country);

    CustomerDTO patchCustomer(Short id, Map<String, Object> updates);
}