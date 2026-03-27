package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Short> {
}