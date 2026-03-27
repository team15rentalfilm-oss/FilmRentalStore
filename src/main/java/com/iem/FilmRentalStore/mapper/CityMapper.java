package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.city.*;
import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.entity.Country;

public class CityMapper {

    //  RequestDTO → Entity
    public static City toEntity(CityRequestDTO dto, Country country) {
        City city = new City();
        city.setCity(dto.getCity());
        city.setCountry(country);
        return city;
    }

    //  Entity → ResponseDTO
    public static CityResponseDTO toResponseDTO(City city) {
        CityResponseDTO dto = new CityResponseDTO();
        dto.setCityId(city.getCityId());
        dto.setCity(city.getCity());
        dto.setCountry(CountryMapper.toDTO(city.getCountry()));
        return dto;
    }

    //  Entity → Lightweight DTO
    public static CityDTO toDTO(City city) {
        CityDTO dto = new CityDTO();
        dto.setCity(city.getCity());
        dto.setCountry(CountryMapper.toDTO(city.getCountry()));
        return dto;
    }
}