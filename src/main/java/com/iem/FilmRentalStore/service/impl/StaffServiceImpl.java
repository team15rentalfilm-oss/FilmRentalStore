package com.iem.FilmRentalStore.service.impl;
import com.iem.FilmRentalStore.entity.Staff;
import com.iem.FilmRentalStore.repository.StaffRepository;
import com.iem.FilmRentalStore.service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public List<Staff> getStaffByFields(Map<String, String> searchParams) {
        Specification<Staff> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            searchParams.forEach((key, value) -> {
                try {
                    root.get(key);

                    // Handle boolean 'active' field string conversions safely
                    if (key.equals("active")) {
                        predicates.add(cb.equal(root.get(key), Boolean.parseBoolean(value)));
                    } else {
                        predicates.add(cb.equal(root.get(key), value));
                    }
                } catch (IllegalArgumentException e) {
                    // Ignore invalid fields sent in the request
                }
            });
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return staffRepository.findAll(spec);
    }

    @Override
    public Staff getStaffById(Integer id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found with id: " + id));
    }

    @Override
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(Integer id, Staff staffDetails) {
        Staff staff = getStaffById(id);

        staff.setFirstName(staffDetails.getFirstName());
        staff.setLastName(staffDetails.getLastName());
        staff.setAddressId(staffDetails.getAddressId());
//        staff.setPicture(staffDetails.getPicture());
        staff.setEmail(staffDetails.getEmail());
        staff.setStoreId(staffDetails.getStoreId());
        staff.setActive(staffDetails.getActive());
        staff.setUsername(staffDetails.getUsername());
        staff.setPassword(staffDetails.getPassword());

        return staffRepository.save(staff);
    }

    @Override
    public Staff patchStaff(Integer id, Map<String, Object> updates) {
        Staff staff = getStaffById(id);

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Staff.class, key);
            if (field != null && !key.equals("staffId") && !key.equals("lastUpdate")) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, staff, value);
            }
        });

        return staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(Integer id) {
        Staff staff = getStaffById(id);
        staffRepository.delete(staff);
    }
}
