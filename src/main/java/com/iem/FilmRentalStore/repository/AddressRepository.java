package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Short> {

    // ✅ GET ALL
    @Query("SELECT a FROM Address a JOIN FETCH a.city c JOIN FETCH c.country")
    List<Address> findAllWithFetch();

    // ✅ GET BY ID
    @Query("SELECT a FROM Address a JOIN FETCH a.city c JOIN FETCH c.country WHERE a.addressId = :id")
    Optional<Address> findByIdWithFetch(Short id);

    // ✅ SEARCH BY ADDRESS
    @Query("SELECT a FROM Address a JOIN FETCH a.city c JOIN FETCH c.country " +
            "WHERE LOWER(a.address) LIKE LOWER(CONCAT('%', :address, '%'))")
    List<Address> findByAddressWithFetch(String address);

    // ✅ SEARCH BY DISTRICT
    @Query("SELECT a FROM Address a JOIN FETCH a.city c JOIN FETCH c.country " +
            "WHERE LOWER(a.district) LIKE LOWER(CONCAT('%', :district, '%'))")
    List<Address> findByDistrictWithFetch(String district);

    // ✅ SEARCH BY CITY
    @Query("SELECT a FROM Address a JOIN FETCH a.city c JOIN FETCH c.country " +
            "WHERE LOWER(c.city) LIKE LOWER(CONCAT('%', :city, '%'))")
    List<Address> findByCityWithFetch(String city);

    // ✅ SEARCH BY COUNTRY
    @Query("SELECT a FROM Address a JOIN FETCH a.city c JOIN FETCH c.country " +
            "WHERE LOWER(c.country.country) LIKE LOWER(CONCAT('%', :country, '%'))")
    List<Address> findByCountryWithFetch(String country);

    Optional<Address> findByAddressAndCity(String address, City city);
}