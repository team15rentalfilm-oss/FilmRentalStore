package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.StaffDTO;
import com.iem.FilmRentalStore.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getStaff(@RequestParam Map<String, String> searchParams) {
        if (searchParams.isEmpty()) {
            return ResponseEntity.ok(staffService.getAllStaff());
        }
        return ResponseEntity.ok(staffService.getStaffByFields(searchParams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(staffService.getStaffById(id));
    }

    @PostMapping
    public ResponseEntity<StaffDTO> createStaff(@Valid @RequestBody StaffDTO staffDTO) {
        return new ResponseEntity<>(staffService.createStaff(staffDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable("id") Integer id,
                                                @Valid @RequestBody StaffDTO staffDTO) {
        return ResponseEntity.ok(staffService.updateStaff(id, staffDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StaffDTO> patchStaff(@PathVariable("id") Integer id,
                                               @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(staffService.patchStaff(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable("id") Integer id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}