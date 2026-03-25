package com.iem.FilmRentalStore.controller;
import com.iem.FilmRentalStore.entity.Staff;
import com.iem.FilmRentalStore.service.StaffService;
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
    public ResponseEntity<List<Staff>> getStaff(@RequestParam Map<String, String> searchParams) {
        if (searchParams.isEmpty()) {
            return ResponseEntity.ok(staffService.getAllStaff());
        }
        return ResponseEntity.ok(staffService.getStaffByFields(searchParams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(staffService.getStaffById(id));
    }

    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        return new ResponseEntity<>(staffService.createStaff(staff), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable("id") Integer id, @RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.updateStaff(id, staff));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Staff> patchStaff(@PathVariable("id") Integer id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(staffService.patchStaff(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable("id") Integer id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}