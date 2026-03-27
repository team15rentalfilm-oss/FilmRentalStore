package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    // ID-based (internal use)
    List<Rental> findByCustomer_CustomerId(Short customerId);

    List<Rental> findByInventory_InventoryId(Integer inventoryId);

    List<Rental> findByStaff_StaffId(Byte staffId);

    // Name-based (user search)
    List<Rental> findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCase(
            String firstName, String lastName);
}