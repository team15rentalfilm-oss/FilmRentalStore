package com.iem.FilmRentalStore.mapper;


import com.iem.FilmRentalStore.dto.country.*;
import com.iem.FilmRentalStore.entity.Country;

public class CountryMapper {

    public static Country toEntity(CountryRequestDTO dto) {
        Country country = new Country();
        country.setCountry(dto.getCountry());
        return country;
    }

    public static CountryResponseDTO toResponseDTO(Country country) {
        CountryResponseDTO dto = new CountryResponseDTO();
        dto.setCountryId(country.getCountryId());
        dto.setCountry(country.getCountry());
        return dto;
    }

    public CountryDTO toDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setCountry(country.getCountry());
        return dto;
    }
}