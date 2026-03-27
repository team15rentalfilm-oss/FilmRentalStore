package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.StaffDTO;
import com.iem.FilmRentalStore.entity.Staff;
import com.iem.FilmRentalStore.repository.StaffRepository;
import com.iem.FilmRentalStore.service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    private StaffDTO mapToDTO(Staff staff) {
        StaffDTO dto = new StaffDTO();
        dto.setStaffId(staff.getStaffId());
        dto.setFirstName(staff.getFirstName());
        dto.setLastName(staff.getLastName());
        dto.setAddressId(staff.getAddressId());
        dto.setEmail(staff.getEmail());
        dto.setStoreId(staff.getStoreId());
        dto.setActive(staff.getActive());
        dto.setUsername(staff.getUsername());
        return dto;
    }

    private Staff mapToEntity(StaffDTO dto) {
        Staff staff = new Staff();
        staff.setFirstName(dto.getFirstName());
        staff.setLastName(dto.getLastName());
        staff.setAddressId(dto.getAddressId());
        staff.setEmail(dto.getEmail());
        staff.setStoreId(dto.getStoreId());
        staff.setActive(dto.getActive());
        staff.setUsername(dto.getUsername());
        return staff;
    }

    private Staff getStaffEntityById(Integer id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found with id: " + id));
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<StaffDTO> getStaffByFields(Map<String, String> searchParams) {
        Specification<Staff> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            searchParams.forEach((key, value) -> {
                try {
                    root.get(key);

                    switch (key) {
                        case "active" -> predicates.add(cb.equal(root.get(key), Boolean.parseBoolean(value)));
                        case "addressId", "storeId", "staffId" -> predicates.add(cb.equal(root.get(key), Integer.valueOf(value)));
                        default -> predicates.add(cb.equal(root.get(key), value));
                    }
                } catch (IllegalArgumentException ignored) {
                    // ignore invalid field names
                }
            });

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return staffRepository.findAll(spec)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public StaffDTO getStaffById(Integer id) {
        return mapToDTO(getStaffEntityById(id));
    }

    @Override
    public StaffDTO createStaff(StaffDTO staffDTO) {
        Staff staff = mapToEntity(staffDTO);
        staff.setStaffId(null);
        return mapToDTO(staffRepository.save(staff));
    }

    @Override
    public StaffDTO updateStaff(Integer id, StaffDTO staffDTO) {
        Staff staff = getStaffEntityById(id);

        staff.setFirstName(staffDTO.getFirstName());
        staff.setLastName(staffDTO.getLastName());
        staff.setAddressId(staffDTO.getAddressId());
        staff.setEmail(staffDTO.getEmail());
        staff.setStoreId(staffDTO.getStoreId());
        staff.setActive(staffDTO.getActive());
        staff.setUsername(staffDTO.getUsername());

        return mapToDTO(staffRepository.save(staff));
    }

    @Override
    public StaffDTO patchStaff(Integer id, Map<String, Object> updates) {
        Staff staff = getStaffEntityById(id);

        updates.forEach((key, value) -> {
            if (key.equals("staffId") || key.equals("lastUpdate")) return;

            switch (key) {
                case "firstName" -> staff.setFirstName((String) value);
                case "lastName" -> staff.setLastName((String) value);
                case "email" -> staff.setEmail((String) value);
                case "addressId" -> staff.setAddressId((Integer) value);
                case "storeId" -> staff.setStoreId((Integer) value);
                case "active" -> staff.setActive((Boolean) value);
                case "username" -> staff.setUsername((String) value);
                case "password" -> staff.setPassword((String) value);
            }
        });

        return mapToDTO(staffRepository.save(staff));
    }

    @Override
    public void deleteStaff(Integer id) {
        Staff staff = getStaffEntityById(id);
        staffRepository.delete(staff);
    }
}