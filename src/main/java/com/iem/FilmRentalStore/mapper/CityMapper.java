package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.city.*;
import com.iem.FilmRentalStore.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public City toEntity(CityRequestDTO dto) {
        City city = new City();
        city.setCity(dto.getCity());
        return city;
    }

    public CityResponseDTO toResponseDTO(City city) {
        CityResponseDTO dto = new CityResponseDTO();
        dto.setCityId(city.getCityId());
        dto.setCity(city.getCity());
        dto.setCountry(CountryMapper.toDTO(city.getCountry()));
        return dto;
    }

    public static CityDTO toDTO(City city) {
        CityDTO dto = new CityDTO();
        dto.setCity(city.getCity());
        dto.setCountry(CountryMapper.toDTO(city.getCountry()));
        return dto;
    }
}
