package com.iem.FilmRentalStore.dto;

import com.iem.FilmRentalStore.entity.Address;
import lombok.*;

public class AddressDTO {

    @Getter @Setter
    public static class Request {
        private String address;
        private String address2;
        private String district;
        private Short cityId;
        private String postalCode;
        private String phone;
        private String location;
    }

    @Getter @Builder
    public static class Response {
        private Short addressId;
        private String address;
        private String district;
        private String cityName;
        private String countryName;
        private String postalCode;
        private String phone;
        private String location;
    }

    // 🔹 ENTITY → DTO (Nested Mapping)
    public static Response toResponse(Address address) {
        return Response.builder()
                .addressId(address.getAddressId())
                .address(address.getAddress())
                .district(address.getDistrict())
                .cityName(address.getCity().getCity())
                .countryName(address.getCity().getCountry().getCountry())
                .postalCode(address.getPostalCode())
                .phone(address.getPhone())
                .location(address.getLocation())
                .build();
    }
}
