package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Short> {

    // 🔍 Search by First Name
    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    // 🔍 Search by Last Name
    List<Customer> findByLastNameContainingIgnoreCase(String lastName);

    // 🔍 Search by First OR Last Name
    List<Customer> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);

    // 🔍 Search by Email
    List<Customer> findByEmailContainingIgnoreCase(String email);

    // 🔍 Search by Store ID (⚠ FIXED TYPE)
    List<Customer> findByStore_StoreId(Short storeId);

    // 🔍 Search by Active Status
    List<Customer> findByActive(Boolean active);

    // 🔍 Search by City
    List<Customer> findByAddress_City_CityContainingIgnoreCase(String city);

    // 🔍 Search by Country
    List<Customer> findByAddress_City_Country_CountryContainingIgnoreCase(String country);
}