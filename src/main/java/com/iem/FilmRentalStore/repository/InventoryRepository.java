package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>, JpaSpecificationExecutor<Inventory> {

    @Query(
            value = "SELECT i FROM Inventory i JOIN FETCH i.film JOIN FETCH i.store",
            countQuery = "SELECT COUNT(i) FROM Inventory i"
    )
    Page<Inventory> findAllWithFilmAndStore(Pageable pageable);

    @Query("SELECT i FROM Inventory i JOIN FETCH i.film JOIN FETCH i.store WHERE i.inventoryId = :id")
    Optional<Inventory> findByIdWithFilmAndStore(Integer id);



    @Query(
            value = "SELECT i FROM Inventory i JOIN FETCH i.film JOIN FETCH i.store WHERE i.film.filmId = :filmId",
            countQuery = "SELECT COUNT(i) FROM Inventory i WHERE i.film.filmId = :filmId"
    )
    Page<Inventory> findByFilmIdWithFetch(Short filmId, Pageable pageable);

    @Query(
            value = "SELECT i FROM Inventory i JOIN FETCH i.film JOIN FETCH i.store WHERE i.store.storeId = :storeId",
            countQuery = "SELECT COUNT(i) FROM Inventory i WHERE i.store.storeId = :storeId"
    )
    Page<Inventory> findByStoreIdWithFetch(Short storeId, Pageable pageable);

    @Query(
            value = "SELECT i FROM Inventory i JOIN FETCH i.film JOIN FETCH i.store WHERE i.film.filmId = :filmId AND i.store.storeId = :storeId",
            countQuery = "SELECT COUNT(i) FROM Inventory i WHERE i.film.filmId = :filmId AND i.store.storeId = :storeId"
    )
    Page<Inventory> findByFilmAndStoreWithFetch(Short filmId, Short storeId, Pageable pageable);



}