package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.country.CountryDTO;
import com.iem.FilmRentalStore.dto.country.CountryRequestDTO;

import java.util.List;

public interface CountryService {

    CountryDTO createCountry(CountryRequestDTO request);

    CountryDTO getCountryById(Integer id);

    CountryDTO getCountryById(Short id);

    List<CountryDTO> getAllCountries();

    CountryDTO updateCountry(Integer id, CountryRequestDTO request);

    CountryDTO updateCountry(Short id, CountryRequestDTO request);
}