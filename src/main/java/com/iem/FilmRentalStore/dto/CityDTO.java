package com.iem.FilmRentalStore.dto;

import com.iem.FilmRentalStore.entity.City;
import com.iem.FilmRentalStore.entity.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CityDTO {

    @Getter
    @Setter
    public static class Request {
        private String city;
        private Short countryId;
    }

    @Getter
    @Builder
    public static class Response {
        private Short cityId;
        private String city;
        private Short countryId;
        private String countryName;
    }

    public static Response toResponse(City city) {
        return Response.builder()
                .cityId(city.getCityId())
                .city(city.getCity())
                .countryId(city.getCountry() != null ? city.getCountry().getCountryId() : null)
                .countryName(
                        city.getCountry() != null
                                ? city.getCountry().getCountry()
                                : null
                )
                .build();
    }

    public static City toEntity(Request dto, Country country) {
        City city = new City();
        city.setCity(dto.getCity());
        city.setCountry(country);
        return city;
    }
}
