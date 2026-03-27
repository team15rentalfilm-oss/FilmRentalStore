package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;

import java.util.List;

public interface CityService {

    CityDTO createCity(CityRequestDTO request);

    CityDTO getCityById(Integer id);

    CityDTO getCityById(Short id);

    List<CityDTO> getAllCities();

    CityDTO updateCity(Integer id, CityRequestDTO request);

    CityDTO updateCity(Short id, CityRequestDTO request);
}