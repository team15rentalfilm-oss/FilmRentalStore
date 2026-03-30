package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.city.CityDTO;
import com.iem.FilmRentalStore.dto.city.CityPatchDTO;
import com.iem.FilmRentalStore.dto.city.CityRequestDTO;
import com.iem.FilmRentalStore.dto.city.CityResponseDTO;
import com.iem.FilmRentalStore.dto.country.CountryResponseDTO;
import com.iem.FilmRentalStore.entity.City;

import java.util.List;

public interface CityService {

    CityDTO createCity(CityRequestDTO request);

    CityResponseDTO getCityById(Short id);
    List<CityResponseDTO> getAllCities();

    CityResponseDTO updateCity(Short id, CityRequestDTO request);

    List<CountryResponseDTO> searchCountries(String name);

    List<CityResponseDTO> searchCitiesByName(String city);

    List<CityResponseDTO> searchCitiesByCountry(String country);

    CityResponseDTO patchCity(Short id, CityPatchDTO request);

    City getOrCreateCity(String cityName, String countryName);

}