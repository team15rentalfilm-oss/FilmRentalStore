package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.inventory.InventoryDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryRequestDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryResponseDTO;
import com.iem.FilmRentalStore.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // 🔥 Helper → Ignore sorting globally
    private Pageable sanitizePageable(Pageable pageable) {
        return PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize()
        );
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<InventoryDTO> createInventory(
            @Valid @RequestBody InventoryRequestDTO request) {
        return ResponseEntity.ok(inventoryService.createInventory(request));
    }

    // ✅ GET BY ID (LIGHTWEIGHT)
    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    // ✅ GET BY ID (DETAILED)
    @GetMapping("/{id}/details")
    public ResponseEntity<InventoryResponseDTO> getInventoryDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(inventoryService.getInventoryDetails(id));
    }

    // ✅ GET ALL (PAGINATION + FILTER)
    @GetMapping
    public ResponseEntity<Page<InventoryDTO>> getAllInventory(
            @RequestParam(required = false) Short filmId,
            @RequestParam(required = false) Short storeId,
            Pageable pageable
    ) {
        pageable = sanitizePageable(pageable);

        return ResponseEntity.ok(
                inventoryService.getAllInventory(filmId, storeId, pageable)
        );
    }

    // ✅ UPDATE (FULL)
    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(
            @PathVariable Integer id,
            @Valid @RequestBody InventoryRequestDTO request) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, request));
    }

    // ✅ PATCH (PARTIAL)
    @PatchMapping("/{id}")
    public ResponseEntity<InventoryDTO> patchInventory(
            @PathVariable Integer id,
            @RequestBody InventoryRequestDTO request) {
        return ResponseEntity.ok(inventoryService.patchInventory(id, request));
    }

}