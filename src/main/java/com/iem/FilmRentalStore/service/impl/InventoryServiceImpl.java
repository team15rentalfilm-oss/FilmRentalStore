package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.entity.Inventory;
import com.iem.FilmRentalStore.repository.InventoryRepository;
import com.iem.FilmRentalStore.service.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> getInventoriesByFields(Map<String, String> searchParams) {
        Specification<Inventory> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            searchParams.forEach((key, value) -> {
                try {
                    root.get(key);
                    predicates.add(cb.equal(root.get(key).as(String.class), value));
                } catch (IllegalArgumentException ignored) {
                }
            });
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return inventoryRepository.findAll(spec);
    }

    @Override
    public Inventory getInventoryById(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Integer id, Inventory inventoryDetails) {
        Inventory inventory = getInventoryById(id);
        inventory.setFilmId(inventoryDetails.getFilmId());
        inventory.setStoreId(inventoryDetails.getStoreId());
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory patchInventory(Integer id, Map<String, Object> updates) {
        Inventory inventory = getInventoryById(id);

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Inventory.class, key);
            if (field != null && !key.equals("inventoryId") && !key.equals("lastUpdate")) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, inventory, value);
            }
        });

        return inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(Integer id) {
        Inventory inventory = getInventoryById(id);
        inventoryRepository.delete(inventory);
    }
}
