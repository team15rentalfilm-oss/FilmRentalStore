package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.InventoryDTO;
import com.iem.FilmRentalStore.entity.Inventory;
import com.iem.FilmRentalStore.repository.InventoryRepository;
import com.iem.FilmRentalStore.service.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // ===================== GET ALL =====================
    @Override
    public List<InventoryDTO> getAllInventories() {
        return inventoryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ===================== SEARCH =====================
    @Override
    public List<InventoryDTO> getInventoriesByFields(Map<String, String> searchParams) {

        Specification<Inventory> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            searchParams.forEach((key, value) -> {
                try {
                    root.get(key);
                    predicates.add(cb.equal(root.get(key).as(String.class), value));
                } catch (IllegalArgumentException ignored) {}
            });

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return inventoryRepository.findAll(spec)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ===================== GET BY ID =====================
    @Override
    public InventoryDTO getInventoryById(Integer id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));

        return toDTO(inventory);
    }

    // ===================== CREATE =====================
    @Override
    public InventoryDTO createInventory(InventoryDTO dto) {
        Inventory inventory = new Inventory();
        mapDtoToEntity(dto, inventory);

        return toDTO(inventoryRepository.save(inventory));
    }

    // ===================== UPDATE (PUT) =====================
    @Override
    public InventoryDTO updateInventory(Integer id, InventoryDTO dto) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));

        mapDtoToEntity(dto, inventory);

        return toDTO(inventoryRepository.save(inventory));
    }

    // ===================== PATCH =====================
    @Override
    public InventoryDTO patchInventory(Integer id, Map<String, Object> updates) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));

        if (updates.containsKey("filmId")) {
            inventory.setFilmId(((Number) updates.get("filmId")).shortValue());
        }

        if (updates.containsKey("storeId")) {
            inventory.setStoreId(((Number) updates.get("storeId")).byteValue());
        }

        return toDTO(inventoryRepository.save(inventory));
    }

    // ===================== DELETE =====================
    @Override
    public void deleteInventory(Integer id) {
        if (!inventoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Inventory not found with id: " + id);
        }
        inventoryRepository.deleteById(id);
    }

    // ===================== MAPPING =====================

    private InventoryDTO toDTO(Inventory inventory) {
        return new InventoryDTO(
                inventory.getInventoryId(),
                inventory.getFilmId(),
                inventory.getStoreId(),
                inventory.getLastUpdate()
        );
    }

    private void mapDtoToEntity(InventoryDTO dto, Inventory inventory) {

        if (dto.getFilmId() != null) {
            inventory.setFilmId(dto.getFilmId());
        }

        if (dto.getStoreId() != null) {
            inventory.setStoreId(dto.getStoreId());
        }
    }
}