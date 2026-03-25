package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findByCustomerId(Short customerId);
    List<Rental> findByInventoryId(Integer inventoryId);
    List<Rental> findByStaffId(Integer staffId);
}
