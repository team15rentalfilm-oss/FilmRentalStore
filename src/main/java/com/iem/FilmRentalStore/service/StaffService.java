package com.iem.FilmRentalStore.service;
import com.iem.FilmRentalStore.entity.Staff;

import java.util.List;
import java.util.Map;

public interface StaffService {
    List<Staff> getAllStaff();
    List<Staff> getStaffByFields(Map<String, String> searchParams);
    Staff getStaffById(Integer id);
    Staff createStaff(Staff staff);
    Staff updateStaff(Integer id, Staff staffDetails);
    Staff patchStaff(Integer id, Map<String, Object> updates);
    void deleteStaff(Integer id);
}