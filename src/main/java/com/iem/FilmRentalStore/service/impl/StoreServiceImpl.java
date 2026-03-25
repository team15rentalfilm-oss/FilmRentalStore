package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.entity.Store;
import com.iem.FilmRentalStore.repository.StoreRepository;
import com.iem.FilmRentalStore.service.StoreService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // Handles "Get by any fields" dynamically
    @Override
    public List<Store> getStoresByFields(Map<String, String> searchParams) {
        Specification<Store> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            searchParams.forEach((key, value) -> {
                try {
                    // Check if the field exists in the entity to avoid crashes on bad queries
                    root.get(key);
                    predicates.add(cb.equal(root.get(key), value));
                } catch (IllegalArgumentException e) {
                    // Ignore fields that don't match entity properties
                }
            });
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return storeRepository.findAll(spec);
    }

    @Override
    public Store getStoreById(Integer id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + id));
    }

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Integer id, Store storeDetails) {
        Store store = getStoreById(id);

        // Update all modifiable fields
        store.setManagerStaffId(storeDetails.getManagerStaffId());
        store.setAddressId(storeDetails.getAddressId());

        return storeRepository.save(store);
    }

    // Handles partial updates
    @Override
    public Store patchStore(Integer id, Map<String, Object> updates) {
        Store store = getStoreById(id);

        updates.forEach((key, value) -> {
            // Use Spring's ReflectionUtils to find the field and update it safely
            Field field = ReflectionUtils.findField(Store.class, key);
            if (field != null && !key.equals("storeId") && !key.equals("lastUpdate")) {
                field.setAccessible(true);
                // Handle type conversion (e.g., if JSON sends Integer but field is Short/Long)
                if (value instanceof Integer && field.getType().equals(Integer.class)) {
                    ReflectionUtils.setField(field, store, value);
                }
                // Add more type checks if necessary depending on your exact data types
            }
        });

        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(Integer id) {
        Store store = getStoreById(id);
        storeRepository.delete(store);
    }
}
