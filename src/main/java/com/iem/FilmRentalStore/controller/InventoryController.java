package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.entity.Inventory;
import com.iem.FilmRentalStore.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Inventory>> getInventories(@RequestParam Map<String, String> searchParams) {
        if (searchParams.isEmpty()) {
            return ResponseEntity.ok(inventoryService.getAllInventories());
        }
        return ResponseEntity.ok(inventoryService.getInventoriesByFields(searchParams));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        return new ResponseEntity<>(inventoryService.createInventory(inventory), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Integer id, @RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventory));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Inventory> patchInventory(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(inventoryService.patchInventory(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}
