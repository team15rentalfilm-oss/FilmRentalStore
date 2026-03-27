package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Short>, JpaSpecificationExecutor<Store> {

    // ID-based (internal use)
    List<Store> findByManager_StaffId(Byte managerId);

    List<Store> findByAddress_AddressId(Short addressId);

    // Location-based (practical search)
    List<Store> findByAddress_City_CityContainingIgnoreCase(String city);
}