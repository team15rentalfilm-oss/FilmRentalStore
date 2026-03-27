package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Short> {

    List<City> findByCountry_CountryId(Short countryId);

}