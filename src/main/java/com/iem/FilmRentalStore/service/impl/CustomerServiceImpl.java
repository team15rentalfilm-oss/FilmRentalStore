package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.customer.*;
import com.iem.FilmRentalStore.entity.*;
import com.iem.FilmRentalStore.mapper.CustomerMapper;
import com.iem.FilmRentalStore.repository.*;
import com.iem.FilmRentalStore.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AddressService addressService;
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
                .orElseThrow(() -> new EntityNotFoundException(
                        "Customer not found with id: " + id));

        return CustomerMapper.toResponseDTO(customer);
    }

    // ✅ PAGINATED GET ALL
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {

        return customerRepository.findAll(pageable)
                .map(CustomerMapper::toDTO);
    }

    // ================= UPDATE =================
    @Override
    public CustomerDTO updateCustomer(Short id, CustomerRequestDTO request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Customer not found with id: " + id));

        Address address = addressService.createAndReturnEntity(request.getAddress());

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Store not found with id: " + request.getStoreId()));

        Customer updated = CustomerMapper.toEntity(request, address, store);

        customer.setFirstName(updated.getFirstName());
        customer.setLastName(updated.getLastName());
        customer.setEmail(updated.getEmail());
        customer.setActive(updated.getActive());
        customer.setAddress(updated.getAddress());
        customer.setStore(updated.getStore());

        Customer saved = customerRepository.save(customer);

        return CustomerMapper.toDTO(saved);
    }

    // ================= SEARCH (PAGINATED) =================

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> getByFirstName(String firstName, Pageable pageable) {

        return customerRepository
                .findByFirstNameContainingIgnoreCase(firstName, pageable)
                .map(CustomerMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> getByLastName(String lastName, Pageable pageable) {

        return customerRepository
                .findByLastNameContainingIgnoreCase(lastName, pageable)
                .map(CustomerMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> getByEmail(String email, Pageable pageable) {

        return customerRepository
                .findByEmailContainingIgnoreCase(email, pageable)
                .map(CustomerMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> getByActive(Boolean active, Pageable pageable) {

        return customerRepository
                .findByActive(active, pageable)
                .map(CustomerMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> getByCity(String city, Pageable pageable) {

        return customerRepository
                .findByAddress_City_CityContainingIgnoreCase(city, pageable)
                .map(CustomerMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> getByCountry(String country, Pageable pageable) {

        return customerRepository
                .findByAddress_City_Country_CountryContainingIgnoreCase(country, pageable)
                .map(CustomerMapper::toDTO);
    }

    // ================= PATCH =================
    @Override
    @Transactional
    public CustomerDTO patchCustomer(Short id, CustomerPatchDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Customer not found with id: " + id));

        // 🔹 BASIC
        if (dto.getFirstName() != null)
            customer.setFirstName(dto.getFirstName());

        if (dto.getLastName() != null)
            customer.setLastName(dto.getLastName());

        if (dto.getEmail() != null)
            customer.setEmail(dto.getEmail());

        if (dto.getActive() != null)
            customer.setActive(dto.getActive());

        // 🔹 STORE
        if (dto.getStoreId() != null) {
            Store store = storeRepository.findById(dto.getStoreId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Store not found with id: " + dto.getStoreId()));

            customer.setStore(store);
        }

        // 🔹 ADDRESS FLOW
        if (dto.getAddress() != null || dto.getCity() != null || dto.getCountry() != null) {

            if (dto.getCity() == null || dto.getCountry() == null) {
                throw new IllegalArgumentException("City and Country must be provided together");
            }

            Country country = countryService.getOrCreateCountry(dto.getCountry());
            City city = cityService.getOrCreateCity(dto.getCity(), country.getCountry());

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