package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.CountryDTO;

import java.util.List;

public interface CountryService {

    CountryDTO.Response create(CountryDTO.Request dto);

    List<CountryDTO.Response> getAll();

    CountryDTO.Response getById(Short id);

    CountryDTO.Response update(Short id, CountryDTO.Request dto);

    void delete(Short id);
}