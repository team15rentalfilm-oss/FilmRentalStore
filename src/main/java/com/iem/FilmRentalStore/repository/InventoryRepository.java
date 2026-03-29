package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>, JpaSpecificationExecutor<Inventory> {

    @Query("SELECT i FROM Inventory i JOIN FETCH i.film JOIN FETCH i.store")
    List<Inventory> findAllWithFilmAndStore();

    @Query("SELECT i FROM Inventory i JOIN FETCH i.film JOIN FETCH i.store WHERE i.inventoryId = :id")
    Optional<Inventory> findByIdWithFilmAndStore(Integer id);

    List<Inventory> findByFilm_FilmId(Short filmId);
    List<Inventory> findByStore_StoreId(Short storeId);

}