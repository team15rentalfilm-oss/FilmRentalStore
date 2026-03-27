package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Short> {

    List<Address> findByAddressContainingIgnoreCase(String address);

    List<Address> findByDistrictContainingIgnoreCase(String district);

    List<Address> findByCity_CityContainingIgnoreCase(String city);
}