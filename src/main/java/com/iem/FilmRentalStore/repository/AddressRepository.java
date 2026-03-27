package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Short> {

    List<Address> findByCity_CityId(Short cityId);

}