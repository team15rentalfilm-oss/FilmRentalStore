package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Short> {

    Page<Customer> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

    Page<Customer> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);

    Page<Customer> findByEmailContainingIgnoreCase(String email, Pageable pageable);

    Page<Customer> findByActive(Boolean active, Pageable pageable);

    Page<Customer> findByAddress_City_CityContainingIgnoreCase(String city, Pageable pageable);

    Page<Customer> findByAddress_City_Country_CountryContainingIgnoreCase(String country, Pageable pageable);
}
