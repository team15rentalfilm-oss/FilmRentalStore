package com.iem.FilmRentalStore.mapper;

import com.iem.FilmRentalStore.dto.staff.*;
import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.Staff;
import com.iem.FilmRentalStore.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper {

    public static Staff toEntity(StaffRequestDTO dto, Address address, Store store) {
        Staff staff = new Staff();

        staff.setFirstName(dto.getFirstName());
        staff.setLastName(dto.getLastName());
        staff.setEmail(dto.getEmail());
        staff.setUsername(dto.getUsername());

        staff.setPassword(dto.getPassword());

        staff.setActive(dto.getActive() != null ? dto.getActive() : true);

        staff.setAddress(address);
        staff.setStore(store);

        return staff;
    }

    public static StaffResponseDTO toResponseDTO(Staff staff) {
        StaffResponseDTO dto = new StaffResponseDTO();

        dto.setStaffId(staff.getStaffId());
        dto.setFirstName(staff.getFirstName());
        dto.setLastName(staff.getLastName());
        dto.setEmail(staff.getEmail());
        dto.setActive(staff.getActive());

        dto.setAddress(AddressMapper.toDTO(staff.getAddress()));
        dto.setStore(StoreMapper.toDTO(staff.getStore()));

        return dto;
    }

    public static StaffDTO toDTO(Staff staff) {
        StaffDTO dto = new StaffDTO();

        dto.setFirstName(staff.getFirstName());
        dto.setLastName(staff.getLastName());

        return dto;
    }
}
