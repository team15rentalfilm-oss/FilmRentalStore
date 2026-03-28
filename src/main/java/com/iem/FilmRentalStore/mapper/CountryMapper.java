package com.iem.FilmRentalStore.mapper;


import com.iem.FilmRentalStore.dto.country.*;
import com.iem.FilmRentalStore.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public Country toEntity(CountryRequestDTO dto) {
        Country country = new Country();
        country.setCountry(dto.getCountry());
        return country;
    }

    public static CountryResponseDTO toResponseDTO(Country country) {
        CountryResponseDTO dto = new CountryResponseDTO();
        dto.setCountryId(country.getCountryId());
        dto.setCountry(country.getCountry());
        dto.setLastUpdate(country.getLastUpdate());
        return dto;
    }

    public static CountryDTO toDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setCountry(country.getCountry());
        return dto;
    }
}