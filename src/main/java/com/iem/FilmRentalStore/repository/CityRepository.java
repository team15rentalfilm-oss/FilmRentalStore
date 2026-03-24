package com.iem.FilmRentalStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iem.FilmRentalStore.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Short> {

}