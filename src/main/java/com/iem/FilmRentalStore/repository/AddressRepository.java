package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Short> {

    List<Address> findByAddressContainingIgnoreCase(String address);

    List<Address> findByDistrictContainingIgnoreCase(String district);

    List<Address> findByCity_CityContainingIgnoreCase(String city);

    List<Address> findByCity_Country_CountryContainingIgnoreCase(String country);

    @Query("SELECT a FROM Address a JOIN FETCH a.city c JOIN FETCH c.country")
    List<Address> findAllWithCityAndCountry();

    @Query("SELECT a FROM Address a JOIN FETCH a.city c JOIN FETCH c.country WHERE a.addressId = :id")
    Optional<Address> findByIdWithCityAndCountry(Short id);
}