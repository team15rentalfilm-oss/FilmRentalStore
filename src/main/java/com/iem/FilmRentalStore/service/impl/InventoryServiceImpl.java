package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.inventory.InventoryDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryRequestDTO;
import com.iem.FilmRentalStore.entity.Film;
import com.iem.FilmRentalStore.entity.Inventory;
import com.iem.FilmRentalStore.entity.Store;
import com.iem.FilmRentalStore.mapper.InventoryMapper;
import com.iem.FilmRentalStore.repository.FilmRepository;
import com.iem.FilmRentalStore.repository.InventoryRepository;
import com.iem.FilmRentalStore.repository.StoreRepository;
import com.iem.FilmRentalStore.service.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final FilmRepository filmRepository;
    private final StoreRepository storeRepository;

    @Override
    public InventoryDTO createInventory(InventoryRequestDTO request) {

        // 🔥 Step 1: Fetch Film
        Film film = filmRepository.findById(request.getFilmId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Film not found with id: " + request.getFilmId()));

        // 🔥 Step 2: Fetch Store
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Store not found with id: " + request.getStoreId()));

        // 🔥 Step 3: Map
        Inventory inventory = InventoryMapper.toEntity(film, store);
        Inventory saved = inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(saved);
    }

    @Override
    public InventoryDTO getInventoryById(Integer id) {
        Inventory inventory = inventoryRepository.findByIdWithFilmAndStore(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));

        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepository.findAllWithFilmAndStore()
                .stream()
                .map(InventoryMapper::toDTO)
                .toList();
    }

    @Override
    public InventoryDTO updateInventory(Integer id, InventoryRequestDTO request) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found with id: " + id));

        Film film = filmRepository.findById(request.getFilmId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Film not found with id: " + request.getFilmId()));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Store not found with id: " + request.getStoreId()));

        inventory.setFilm(film);
        inventory.setStore(store);

        Inventory updated = inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(updated);
    }

    @Override
    public List<InventoryDTO> getByFilmId(Short filmId) {
        return inventoryRepository.findByFilm_FilmId(filmId)
                .stream()
                .map(InventoryMapper::toDTO)
                .toList();
    }

    @Override
    public List<InventoryDTO> getByStoreId(Short storeId) {
        return inventoryRepository.findByStore_StoreId(storeId)
                .stream()
                .map(InventoryMapper::toDTO)
                .toList();
    }


    @Override
    public InventoryDTO patchInventory(Integer id, InventoryRequestDTO request) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        if (request.getFilmId() != null) {
            Film film = filmRepository.findById(request.getFilmId())
                    .orElseThrow(() -> new EntityNotFoundException("Film not found"));
            inventory.setFilm(film);
        }

        if (request.getStoreId() != null) {
            Store store = storeRepository.findById(request.getStoreId())
                    .orElseThrow(() -> new EntityNotFoundException("Store not found"));
            inventory.setStore(store);
        }

        Inventory updated = inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(updated);
    }


}