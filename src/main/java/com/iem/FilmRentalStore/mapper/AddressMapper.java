package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.address.AddressDTO;
import com.iem.FilmRentalStore.dto.address.AddressRequestDTO;
import com.iem.FilmRentalStore.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    // DTO → Entity
    public static Address toEntity(AddressRequestDTO dto) {
        Address address = new Address();
        address.setAddress(dto.getAddress());
        address.setAddress2(dto.getAddress2());
        address.setDistrict(dto.getDistrict());
        address.setPostalCode(dto.getPostalCode());
        address.setPhone(dto.getPhone());
        return address;
    }

    // Entity → DTO
    // Entity → DTO (RESPONSE)
    public static AddressDTO toDTO(Address address) {
        AddressDTO dto = new AddressDTO();

        dto.setAddress(address.getAddress());
        dto.setAddress2(address.getAddress2());
        dto.setDistrict(address.getDistrict());
        dto.setPostalCode(address.getPostalCode());
        dto.setPhone(address.getPhone());

        dto.setCity(CityMapper.toDTO(address.getCity())); // ✅ now correct

        return dto;
    }
}