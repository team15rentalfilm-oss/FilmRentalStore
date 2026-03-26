package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.InventoryDTO;
import com.iem.FilmRentalStore.service.InventoryService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getInventories(@RequestParam Map<String, String> params) {
        if (params.isEmpty())
            return ResponseEntity.ok(inventoryService.getAllInventories());

        return ResponseEntity.ok(inventoryService.getInventoriesByFields(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO dto) {
        return new ResponseEntity<>(inventoryService.createInventory(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Integer id,
                                                        @RequestBody InventoryDTO dto) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InventoryDTO> patchInventory(@PathVariable Integer id,
                                                       @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(inventoryService.patchInventory(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}