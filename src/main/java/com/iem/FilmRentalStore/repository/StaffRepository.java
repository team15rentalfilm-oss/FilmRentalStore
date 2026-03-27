package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Short>, JpaSpecificationExecutor<Staff> {

    // ID-based (internal use)
    List<Staff> findByStore_StoreId(Integer storeId);

    // Name-based (user search)
    List<Staff> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);
}