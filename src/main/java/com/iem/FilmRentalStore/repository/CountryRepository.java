package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Short> {

    List<Country> findByCountryContainingIgnoreCase(String country);
}