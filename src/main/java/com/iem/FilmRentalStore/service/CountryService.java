package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.country.CountryDTO;
import com.iem.FilmRentalStore.dto.country.CountryRequestDTO;
import com.iem.FilmRentalStore.dto.country.CountryResponseDTO;

import java.util.List;

public interface CountryService {

    CountryDTO createCountry(CountryRequestDTO request);

    CountryDTO updateCountry(Short id, CountryRequestDTO request);

    CountryResponseDTO getCountryById(Short id);

    List<CountryResponseDTO> getAllCountries();

    List<CountryResponseDTO> searchCountries(String name);
}