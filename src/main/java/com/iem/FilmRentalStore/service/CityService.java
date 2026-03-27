package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.CityDTO;

import java.util.List;

public interface CityService {

    CityDTO.Response create(CityDTO.Request dto);

    List<CityDTO.Response> getAll();

    CityDTO.Response getById(Short id);

    CityDTO.Response update(Short id, CityDTO.Request dto);

    void delete(Short id);

    List<CityDTO.Response> getByCountry(Short countryId);
}