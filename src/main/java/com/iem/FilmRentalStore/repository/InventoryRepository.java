package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>, JpaSpecificationExecutor<Inventory> {

    List<Inventory> findByFilm_FilmId(Integer filmId);

    List<Inventory> findByStore_StoreId(Integer storeId);
}