package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.StaffDTO;

import java.util.List;
import java.util.Map;

public interface StaffService {
    List<StaffDTO> getAllStaff();
    List<StaffDTO> getStaffByFields(Map<String, String> searchParams);
    StaffDTO getStaffById(Integer id);
    StaffDTO createStaff(StaffDTO staffDTO);
    StaffDTO updateStaff(Integer id, StaffDTO staffDTO);
    StaffDTO patchStaff(Integer id, Map<String, Object> updates);
    void deleteStaff(Integer id);
}