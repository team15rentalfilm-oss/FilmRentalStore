package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Short> {

    List<City> findByCityContainingIgnoreCase(String city);

    List<City> findByCountry_CountryContainingIgnoreCase(String country);
    Optional<Country> findByCountryIgnoreCase(String country);
    Optional<City> findByCityIgnoreCaseAndCountry(String city, Country country);
}