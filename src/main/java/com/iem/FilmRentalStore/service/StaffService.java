package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.staff.StaffPatchDTO;
import com.iem.FilmRentalStore.dto.staff.StaffRequestDTO;
import com.iem.FilmRentalStore.dto.staff.StaffResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface StaffService {

    StaffResponseDTO createStaff(StaffRequestDTO request);

    StaffResponseDTO getStaffById(Short id);

    Page<StaffResponseDTO> getAllStaff(Pageable pageable);

    Page<StaffResponseDTO> searchStaff(String name, Short storeId, Pageable pageable);

    Page<StaffResponseDTO> getStaffByCity(String city, Pageable pageable);

    Page<StaffResponseDTO> getStaffByCountry(String country, Pageable pageable);

    StaffResponseDTO updateStaff(Short id, StaffRequestDTO request);

    public StaffResponseDTO patchStaff(Short id, StaffPatchDTO dto);
}
