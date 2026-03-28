package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.customer.CustomerDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerRequestDTO;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.Customer;
import com.iem.FilmRentalStore.entity.Store;
import com.iem.FilmRentalStore.mapper.CustomerMapper;
import com.iem.FilmRentalStore.repository.CustomerRepository;
import com.iem.FilmRentalStore.repository.StoreRepository;
import com.iem.FilmRentalStore.service.AddressService;
import com.iem.FilmRentalStore.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AddressService addressService;

    // ================= CREATE =================
    @Override
    public CustomerDTO createCustomer(CustomerRequestDTO request) {

        Address address = addressService.createAndReturnEntity(request.getAddress());

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Store not found with id: " + request.getStoreId()));

        Customer customer = CustomerMapper.toEntity(request, address, store);
        customer.setCreateDate(LocalDateTime.now());

        Customer saved = customerRepository.save(customer);

        return CustomerMapper.toDTO(saved);
    }

    // ================= GET =================
    @Override
    public CustomerDTO getCustomerById(Short id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        return CustomerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    // ================= UPDATE =================
    @Override
    public CustomerDTO updateCustomer(Short id, CustomerRequestDTO request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        Address address = addressService.createAndReturnEntity(request.getAddress());

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Store not found with id: " + request.getStoreId()));

        Customer updatedData = CustomerMapper.toEntity(request, address, store);

        customer.setFirstName(updatedData.getFirstName());
        customer.setLastName(updatedData.getLastName());
        customer.setEmail(updatedData.getEmail());
        customer.setActive(updatedData.getActive());
        customer.setAddress(updatedData.getAddress());
        customer.setStore(updatedData.getStore());

        Customer saved = customerRepository.save(customer);

        return CustomerMapper.toDTO(saved);
    }

    // ================= SEARCH =================

    @Override
    public List<CustomerDTO> getByFirstName(String firstName) {
        return customerRepository.findByFirstNameContainingIgnoreCase(firstName)
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    @Override
    public List<CustomerDTO> getByLastName(String lastName) {
        return customerRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    @Override
    public List<CustomerDTO> getByEmail(String email) {
        return customerRepository.findByEmailContainingIgnoreCase(email)
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    @Override
    public List<CustomerDTO> getByActive(Boolean active) {
        return customerRepository.findByActive(active)
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    @Override
    public List<CustomerDTO> getByCity(String city) {
        return customerRepository.findByAddress_City_CityContainingIgnoreCase(city)
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    @Override
    public List<CustomerDTO> getByCountry(String country) {
        return customerRepository.findByAddress_City_Country_CountryContainingIgnoreCase(country)
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    // ================= PATCH =================

    @Override
    public CustomerDTO patchCustomer(Short id, Map<String, Object> updates) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        if (updates.containsKey("firstName")) {
            customer.setFirstName((String) updates.get("firstName"));
        }

        if (updates.containsKey("lastName")) {
            customer.setLastName((String) updates.get("lastName"));
        }

        if (updates.containsKey("email")) {
            customer.setEmail((String) updates.get("email"));
        }

        if (updates.containsKey("active")) {
            customer.setActive((Boolean) updates.get("active"));
        }

        Customer saved = customerRepository.save(customer);

        return CustomerMapper.toDTO(saved);
    }
}