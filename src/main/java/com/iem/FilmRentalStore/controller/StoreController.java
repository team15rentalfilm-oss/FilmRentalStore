package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.entity.Store;
import com.iem.FilmRentalStore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // GET ALL & GET BY ANY FIELDS
    @GetMapping
    public ResponseEntity<List<Store>> getStores(@RequestParam Map<String, String> searchParams) {
        if (searchParams.isEmpty()) {
            return ResponseEntity.ok(storeService.getAllStores());
        }
        return ResponseEntity.ok(storeService.getStoresByFields(searchParams));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    // POST (Create)
    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        return new ResponseEntity<>(storeService.createStore(store), HttpStatus.CREATED);
    }

    // PUT (Full Update)
    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable("id") Integer id, @RequestBody Store store) {
        return ResponseEntity.ok(storeService.updateStore(id, store));
    }

    // PATCH (Partial Update)
    @PatchMapping("/{id}")
    public ResponseEntity<Store> patchStore(@PathVariable("id") Integer id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(storeService.patchStore(id, updates));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable("id") Integer id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}
