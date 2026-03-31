package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Short> {

    Optional<City> findByCityIgnoreCaseAndCountry(String city, Country country);
    @Query("SELECT c FROM City c JOIN FETCH c.country")
    List<City> findAllWithCountry();

    @Query("SELECT c FROM City c JOIN FETCH c.country WHERE c.cityId = :id")
    Optional<City> findByIdWithCountry(@Param("id") Short id);

    @Query("SELECT c FROM City c JOIN FETCH c.country WHERE LOWER(c.city) LIKE LOWER(CONCAT('%', :city, '%'))")
    List<City> findByCityWithCountry(@Param("city") String city);

    @Query("SELECT c FROM City c JOIN FETCH c.country WHERE LOWER(c.country.country) LIKE LOWER(CONCAT('%', :country, '%'))")
    List<City> findByCountryNameWithCountry(@Param("country") String country);

    Page<City> findByCityContainingIgnoreCase(String city, Pageable pageable);

    Page<City> findByCountry_CountryContainingIgnoreCase(String country, Pageable pageable);

}