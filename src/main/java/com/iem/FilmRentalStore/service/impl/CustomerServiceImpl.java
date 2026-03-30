package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.customer.CustomerDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerPatchDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerRequestDTO;
import com.iem.FilmRentalStore.dto.customer.CustomerResponseDTO;
import com.iem.FilmRentalStore.entity.*;
import com.iem.FilmRentalStore.mapper.CustomerMapper;
import com.iem.FilmRentalStore.repository.*;
import com.iem.FilmRentalStore.service.AddressService;
import com.iem.FilmRentalStore.service.CityService;
import com.iem.FilmRentalStore.service.CountryService;
import com.iem.FilmRentalStore.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AddressService addressService;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final CountryService countryService;
    private final CityService cityService;

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
    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerById(Short id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        return CustomerMapper.toResponseDTO(customer);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional
    public CustomerDTO patchCustomer(Short id, CustomerPatchDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        // ================= BASIC =================
        if (dto.getFirstName() != null)
            customer.setFirstName(dto.getFirstName());

        if (dto.getLastName() != null)
            customer.setLastName(dto.getLastName());

        if (dto.getEmail() != null)
            customer.setEmail(dto.getEmail());

        if (dto.getActive() != null)
            customer.setActive(dto.getActive());

        // ================= STORE =================
        if (dto.getStoreId() != null) {

            Store store = storeRepository.findById(dto.getStoreId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Store not found with id: " + dto.getStoreId()));

            customer.setStore(store);
        }

        // ================= ADDRESS FLOW (USING SERVICES 🔥) =================
        if (dto.getAddress() != null || dto.getCity() != null || dto.getCountry() != null) {

            // 🔹 Validate minimum required fields
            if (dto.getCountry() == null || dto.getCity() == null) {
                throw new IllegalArgumentException("City and Country must be provided together");
            }

            // 🔹 1. Country → reuse service
            Country country = countryService.getOrCreateCountry(dto.getCountry());

            // 🔹 2. City → reuse service
            City city = cityService.getOrCreateCity(dto.getCity(), country.getCountry());

            // 🔹 3. Address → find or create
            Address address = addressRepository
                    .findByAddressAndCity(dto.getAddress(), city)
                    .orElseGet(() -> {
                        Address a = new Address();
                        a.setAddress(dto.getAddress());
                        a.setAddress2(dto.getAddress2());
                        a.setDistrict(dto.getDistrict());
                        a.setPostalCode(dto.getPostalCode());
                        a.setPhone(dto.getPhone());
                        a.setCity(city);
                        return addressRepository.save(a);
                    });

            customer.setAddress(address);
        }

        Customer saved = customerRepository.save(customer);

        return CustomerMapper.toDTO(saved);
    }
}