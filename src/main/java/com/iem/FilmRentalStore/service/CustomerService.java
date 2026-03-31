package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.customer.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerRequestDTO request);

    CustomerResponseDTO getCustomerById(Short id);

    Page<CustomerDTO> getAllCustomers(Pageable pageable);

    CustomerDTO updateCustomer(Short id, CustomerRequestDTO request);

    Page<CustomerDTO> getByFirstName(String firstName, Pageable pageable);
    Page<CustomerDTO> getByLastName(String lastName, Pageable pageable);
    Page<CustomerDTO> getByEmail(String email, Pageable pageable);
    Page<CustomerDTO> getByActive(Boolean active, Pageable pageable);
    Page<CustomerDTO> getByCity(String city, Pageable pageable);
    Page<CustomerDTO> getByCountry(String country, Pageable pageable);

    CustomerDTO patchCustomer(Short id, CustomerPatchDTO dto);
}
