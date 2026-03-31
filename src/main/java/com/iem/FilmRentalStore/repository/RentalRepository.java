package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Inventory;
import com.iem.FilmRentalStore.entity.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {


    @EntityGraph(attributePaths = {
            "inventory",
            "inventory.film",
            "customer",
            "staff"
    })
    Page<Rental> findByCustomer_CustomerId(Short customerId, Pageable pageable);

    @EntityGraph(attributePaths = {
            "inventory",
            "inventory.film",
            "customer",
            "staff"
    })
    Page<Rental> findByInventory_InventoryId(Integer inventoryId, Pageable pageable);

    @EntityGraph(attributePaths = {
            "inventory",
            "inventory.film",
            "customer",
            "staff"
    })
    Page<Rental> findByStaff_StaffId(Byte staffId, Pageable pageable);

    @EntityGraph(attributePaths = {
            "inventory",
            "inventory.film",
            "customer",
            "staff"
    })
    Page<Rental> findAll(Pageable pageable);

    boolean existsByInventoryAndReturnDateIsNull(Inventory inventory);

    List<Rental> findByCustomer_FirstNameContainingIgnoreCaseOrCustomer_LastNameContainingIgnoreCase(
            String firstName, String lastName);

}
