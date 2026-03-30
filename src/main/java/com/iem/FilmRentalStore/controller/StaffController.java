package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.staff.*;
import com.iem.FilmRentalStore.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public StaffResponseDTO create(@Valid @RequestBody StaffRequestDTO request) {
        return staffService.createStaff(request);
    }

    @GetMapping("/{id}")
    public StaffResponseDTO getById(@PathVariable Short id) {
        return staffService.getStaffById(id);
    }

    @GetMapping
    public Page<StaffResponseDTO> getAll(Pageable pageable) {

        pageable = sanitizePageable(pageable);

        return staffService.getAllStaff(pageable);
    }

    private static final List<String> ALLOWED_SORTS =
            List.of("staffId", "firstName", "lastName", "email");

    private Pageable sanitizePageable(Pageable pageable) {

        if (pageable.getSort().isSorted()) {

            for (Sort.Order order : pageable.getSort()) {

                String property = order.getProperty();

                // ❌ Invalid Swagger format
                if (property.contains("[") || property.contains("]") || property.contains("\"")) {
                    return defaultPage(pageable);
                }

                // ❌ Unknown field
                if (!ALLOWED_SORTS.contains(property)) {
                    return defaultPage(pageable);
                }
            }
        }

        return pageable;
    }

    private Pageable defaultPage(Pageable pageable) {
        return PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("staffId").ascending()
        );
    }

    @GetMapping("/search")
    public Page<StaffResponseDTO> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Short storeId,
            Pageable pageable) {

        pageable = sanitizePageable(pageable); // 🔥 ADD THIS

        return staffService.searchStaff(name, storeId, pageable);
    }

    @GetMapping("/city")
    public Page<StaffResponseDTO> getByCity(
            @RequestParam String city,
            Pageable pageable) {

        pageable = sanitizePageable(pageable); // 🔥 ADD

        return staffService.getStaffByCity(city, pageable);
    }

    @GetMapping("/country")
    public Page<StaffResponseDTO> getByCountry(
            @RequestParam String country,
            Pageable pageable) {

        pageable = sanitizePageable(pageable); // 🔥 ADD

        return staffService.getStaffByCountry(country, pageable);
    }

    @PutMapping("/{id}")
    public StaffResponseDTO update(
            @PathVariable Short id,
            @Valid @RequestBody StaffRequestDTO request) {

        return staffService.updateStaff(id, request);
    }

    @PatchMapping("/{id}")
    public StaffResponseDTO patch(
            @PathVariable Short id,
            @RequestBody StaffPatchDTO dto) {

        return staffService.patchStaff(id, dto);
    }
}