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

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AddressService addressService;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO createCustomer(CustomerRequestDTO request) {

        Address address = addressService.createAndReturnEntity(request.getAddress());

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Store not found with id: " + request.getStoreId()));

        Customer customer = CustomerMapper.toEntity(request, address, store);

        customer.setCreateDate(LocalDateTime.now());

        Customer saved = customerRepository.save(customer);

        return customerMapper.toDTO(saved);
    }

    @Override
    public CustomerDTO getCustomerById(Short id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        return customerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDTO)
                .toList();
    }

    @Override
    public CustomerDTO updateCustomer(Short id, CustomerRequestDTO request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        Address address = addressService.createAndReturnEntity(request.getAddress());

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Store not found with id: " + request.getStoreId()));

        // 🔥 Reuse mapper for consistency
        Customer updatedData = CustomerMapper.toEntity(request, address, store);

        // Copy fields
        customer.setFirstName(updatedData.getFirstName());
        customer.setLastName(updatedData.getLastName());
        customer.setEmail(updatedData.getEmail());
        customer.setActive(updatedData.getActive());
        customer.setAddress(updatedData.getAddress());
        customer.setStore(updatedData.getStore());

        Customer saved = customerRepository.save(customer);

        return customerMapper.toDTO(saved);
    }
}