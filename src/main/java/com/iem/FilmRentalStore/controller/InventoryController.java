package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.inventory.InventoryDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryRequestDTO;
import com.iem.FilmRentalStore.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public InventoryDTO createInventory(@Valid @RequestBody InventoryRequestDTO request) {
        return inventoryService.createInventory(request);
    }

    @GetMapping("/{id}")
    public InventoryDTO getInventoryById(@PathVariable Integer id) {
        return inventoryService.getInventoryById(id);
    }

    @GetMapping
    public List<InventoryDTO> getAllInventory(
            @RequestParam(required = false) Short filmId,
            @RequestParam(required = false) Short storeId) {

        if (filmId != null) {
            return inventoryService.getByFilmId(filmId);
        }

        if (storeId != null) {
            return inventoryService.getByStoreId(storeId);
        }

        return inventoryService.getAllInventory();
    }

    @PutMapping("/{id}")
    public InventoryDTO updateInventory(@PathVariable Integer id,
                                        @Valid @RequestBody InventoryRequestDTO request) {
        return inventoryService.updateInventory(id, request);
    }

    @PatchMapping("/{id}")
    public InventoryDTO patchInventory(@PathVariable Integer id,
                                       @RequestBody InventoryRequestDTO request) {
        return inventoryService.patchInventory(id, request);
    }
}