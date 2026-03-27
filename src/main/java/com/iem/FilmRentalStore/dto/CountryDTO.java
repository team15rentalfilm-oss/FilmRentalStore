package com.iem.FilmRentalStore.dto;

import com.iem.FilmRentalStore.entity.Country;
import lombok.*;

public class CountryDTO {

    // 🔹 REQUEST
    @Getter @Setter
    public static class Request {
        private String country;
    }

    // 🔹 RESPONSE
    @Getter @Builder
    public static class Response {
        private Short countryId;
        private String country;
    }

    // 🔹 DTO → ENTITY
    public static Country toEntity(Request dto) {
        Country c = new Country();
        c.setCountry(dto.getCountry());
        return c;
    }

    // 🔹 ENTITY → DTO
    public static Response toResponse(Country c) {
        return Response.builder()
                .countryId(c.getCountryId())
                .country(c.getCountry())
                .build();
    }
}