package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.customer.*;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.Customer;
import com.iem.FilmRentalStore.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDTO dto, Address address, Store store) {
        Customer customer = new Customer();

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setActive(dto.getActive() != null ? dto.getActive() : true);

        customer.setAddress(address);
        customer.setStore(store);

        return customer;
    }

    public static CustomerResponseDTO toResponseDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setCustomerId(customer.getCustomerId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setActive(customer.getActive());

        dto.setAddress(AddressMapper.toDTO(customer.getAddress()));
        dto.setStore(StoreMapper.toDTO(customer.getStore()));

        dto.setCreateDate(customer.getCreateDate());

        return dto;
    }

    public static CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getCustomerId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());

        return dto;
    }
}
