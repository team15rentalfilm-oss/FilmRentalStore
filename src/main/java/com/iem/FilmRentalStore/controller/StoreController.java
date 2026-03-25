package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.entity.Store;
import com.iem.FilmRentalStore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Returns JSON instead of HTML views
@RequestMapping("/api/stores") // Best practice to prefix API routes
@CrossOrigin(origins = "http://localhost:3000") // Allow your frontend to access this
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    // GET: Fetch all stores
    @GetMapping
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // GET: Fetch a single store by ID
    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Integer id) {
        return storeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Create a new store
    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    // PUT: Update an existing store
    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Integer id, @RequestBody Store storeDetails) {
        return storeRepository.findById(id)
                .map(store -> {
                    // Update fields here (example: store.setName(storeDetails.getName());)
                    // Assuming Store entity has appropriate setters
                    storeDetails.setAddressId(id);
                    return ResponseEntity.ok(storeRepository.save(storeDetails));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE: Remove a store
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Integer id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}