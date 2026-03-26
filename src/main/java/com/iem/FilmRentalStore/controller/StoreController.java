package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.StoreDTO;
import com.iem.FilmRentalStore.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin(origins = "http://localhost:3000")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Integer id) {
        return ResponseEntity.ok(storeService.getStoreById(id.byteValue()));
    }

    @PostMapping
    public ResponseEntity<StoreDTO> createStore(@Valid @RequestBody StoreDTO storeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(storeService.createStore(storeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Integer id,
                                                @Valid @RequestBody StoreDTO storeDTO) {
        return ResponseEntity.ok(storeService.updateStore(id.byteValue(), storeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id.byteValue());
        return ResponseEntity.noContent().build();
    }
}