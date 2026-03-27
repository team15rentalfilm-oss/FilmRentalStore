package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.address.*;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.City;

public class AddressMapper {

    // RequestDTO → Entity
    public static Address toEntity(AddressRequestDTO dto, City city) {
        Address address = new Address();

        address.setAddress(dto.getAddress());
        address.setAddress2(dto.getAddress2());
        address.setDistrict(dto.getDistrict());
        address.setPostalCode(dto.getPostalCode());
        address.setPhone(dto.getPhone());
        address.setCity(city);

        return address;
    }

    // Entity → ResponseDTO
    public static AddressResponseDTO toResponseDTO(Address address) {
        AddressResponseDTO dto = new AddressResponseDTO();

        dto.setAddressId(address.getAddressId());
        dto.setAddress(address.getAddress());
        dto.setAddress2(address.getAddress2());
        dto.setDistrict(address.getDistrict());
        dto.setPostalCode(address.getPostalCode());
        dto.setPhone(address.getPhone());
        dto.setCity(CityMapper.toDTO(address.getCity()));

        return dto;
    }

    // Entity → Lightweight DTO
    public static AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();

        dto.setAddress(address.getAddress());
        dto.setDistrict(address.getDistrict());
        dto.setPostalCode(address.getPostalCode());
        dto.setCity(CityMapper.toDTO(address.getCity()));

        return dto;
    }
}