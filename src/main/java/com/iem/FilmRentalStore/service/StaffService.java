package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.staff.StaffPatchDTO;
import com.iem.FilmRentalStore.dto.staff.StaffRequestDTO;
import com.iem.FilmRentalStore.dto.staff.StaffResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface StaffService {

    // ✅ CREATE
    StaffResponseDTO createStaff(StaffRequestDTO request);

    // ✅ GET BY ID
    StaffResponseDTO getStaffById(Short id);

    // ✅ GET ALL (Pagination)
    Page<StaffResponseDTO> getAllStaff(Pageable pageable);

    // ✅ SEARCH (name OR store)
    Page<StaffResponseDTO> searchStaff(String name, Short storeId, Pageable pageable);

    // ✅ FILTER BY CITY
    Page<StaffResponseDTO> getStaffByCity(String city, Pageable pageable);

    // ✅ FILTER BY COUNTRY
    Page<StaffResponseDTO> getStaffByCountry(String country, Pageable pageable);

    // ✅ FULL UPDATE
    StaffResponseDTO updateStaff(Short id, StaffRequestDTO request);

    // ✅ PARTIAL UPDATE (PATCH)
    public StaffResponseDTO patchStaff(Short id, StaffPatchDTO dto);
}