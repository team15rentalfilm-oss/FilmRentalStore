package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.staff.StaffDTO;
import com.iem.FilmRentalStore.dto.staff.StaffRequestDTO;

import java.util.List;

public interface StaffService {

    StaffDTO createStaff(StaffRequestDTO request);

    StaffDTO getStaffById(Short id);

    List<StaffDTO> getAllStaff();

    StaffDTO updateStaff(Short id, StaffRequestDTO request);
}