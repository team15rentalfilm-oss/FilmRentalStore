package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.store.StoreDTO;
import com.iem.FilmRentalStore.dto.store.StoreRequestDTO;
import com.iem.FilmRentalStore.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public StoreDTO createStore(@Valid @RequestBody StoreRequestDTO request) {
        return storeService.createStore(request);
    }

    @GetMapping("/{id}")
    public StoreDTO getStoreById(@PathVariable Short id) {
        return storeService.getStoreById(id);
    }

    @GetMapping
    public List<StoreDTO> getAllStores() {
        return storeService.getAllStores();
    }

    @PutMapping("/{id}")
    public StoreDTO updateStore(@PathVariable Short id,
                                @Valid @RequestBody StoreRequestDTO request) {
        return storeService.updateStore(id, request);
    }
}