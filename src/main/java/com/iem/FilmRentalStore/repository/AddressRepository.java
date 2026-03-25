package com.iem.FilmRentalStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iem.FilmRentalStore.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Short> {

}