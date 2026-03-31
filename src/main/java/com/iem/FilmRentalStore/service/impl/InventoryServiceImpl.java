package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.inventory.InventoryDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryRequestDTO;
import com.iem.FilmRentalStore.dto.inventory.InventoryResponseDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final FilmRepository filmRepository;
    private final StoreRepository storeRepository;

    @Override
    public InventoryDTO createInventory(InventoryRequestDTO request) {

        Film film = filmRepository.findById(request.getFilmId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Film not found with id: " + request.getFilmId()));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Store not found with id: " + request.getStoreId()));

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
    public Page<InventoryDTO> getAllInventory(Short filmId, Short storeId, Pageable pageable) {

        Page<Inventory> page;

        if (filmId != null && storeId != null) {
            page = inventoryRepository.findByFilmAndStoreWithFetch(filmId, storeId, pageable);
        } else if (filmId != null) {
            page = inventoryRepository.findByFilmIdWithFetch(filmId, pageable);
        } else if (storeId != null) {
            page = inventoryRepository.findByStoreIdWithFetch(storeId, pageable);
        } else {
            page = inventoryRepository.findAllWithFilmAndStore(pageable);
        }

        return page.map(InventoryMapper::toDTO);
    }

    @Transactional
    @Override
    public InventoryDTO updateInventory(Integer id, InventoryRequestDTO request) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        Film film = filmRepository.findById(request.getFilmId())
                .orElseThrow(() -> new EntityNotFoundException("Film not found"));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));

        inventory.setFilm(film);
        inventory.setStore(store);

        inventoryRepository.save(inventory);

        Inventory fetched = inventoryRepository.findByIdWithFilmAndStore(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        return InventoryMapper.toDTO(fetched);
    }



    @Transactional
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

        inventoryRepository.save(inventory);

        Inventory fetched = inventoryRepository.findByIdWithFilmAndStore(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        return InventoryMapper.toDTO(fetched);
    }

    @Override
    public InventoryResponseDTO getInventoryDetails(Integer id) {
        Inventory inventory = inventoryRepository.findByIdWithFilmAndStore(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        return InventoryMapper.toResponseDTO(inventory);
    }



}
