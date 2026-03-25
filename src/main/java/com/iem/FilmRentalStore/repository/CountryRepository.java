package com.iem.FilmRentalStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iem.FilmRentalStore.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Short> {

}