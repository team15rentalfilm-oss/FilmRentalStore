package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityPatchDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import com.iem.FilmRentalStore.dto.city.CityResponseDTO;
import com.iem.FilmRentalStore.dto.country.CountryResponseDTO;
import com.iem.FilmRentalStore.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

    CityDTO createCity(CityRequestDTO request);

    CityResponseDTO getCityById(Short id);

    CityResponseDTO updateCity(Short id, CityRequestDTO request);

    List<CountryResponseDTO> searchCountries(String name);

    CityResponseDTO patchCity(Short id, CityPatchDTO request);

    City getOrCreateCity(String cityName, String countryName);

    Page<CityResponseDTO> getAllCities(Pageable pageable);

    Page<CityResponseDTO> searchCitiesByName(String city, Pageable pageable);

    Page<CityResponseDTO> searchCitiesByCountry(String country, Pageable pageable);
}