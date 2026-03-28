package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.staff.StaffDTO;
import com.iem.FilmRentalStore.dto.staff.StaffRequestDTO;
import com.iem.FilmRentalStore.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public StaffDTO createStaff(@Valid @RequestBody StaffRequestDTO request) {
        return staffService.createStaff(request);
    }

    @GetMapping("/{id}")
    public StaffDTO getStaffById(@PathVariable Short id) {
        return staffService.getStaffById(id);
    }

    @GetMapping
    public List<StaffDTO> getAllStaff() {
        return staffService.getAllStaff();
    }

    @PutMapping("/{id}")
    public StaffDTO updateStaff(@PathVariable Short id,
                                @Valid @RequestBody StaffRequestDTO request) {
        return staffService.updateStaff(id, request);
    }
}