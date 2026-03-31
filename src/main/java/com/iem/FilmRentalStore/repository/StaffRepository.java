package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Short> {

    @EntityGraph(attributePaths = {
            "address",
            "address.city",
            "address.city.country",
            "store"
    })
    Page<Staff> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName, Pageable pageable);
    @EntityGraph(attributePaths = {
            "address",
            "address.city",
            "address.city.country",
            "store"
    })
    Page<Staff> findByStore_StoreId(Short storeId, Pageable pageable);
    @EntityGraph(attributePaths = {
            "address",
            "address.city",
            "address.city.country",
            "store"
    })
    Page<Staff> findByAddress_City_CityContainingIgnoreCase(String city, Pageable pageable);
    @EntityGraph(attributePaths = {
            "address",
            "address.city",
            "address.city.country",
            "store"
    })
    Page<Staff> findByAddress_City_Country_CountryContainingIgnoreCase(String country, Pageable pageable);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {
            "address",
            "address.city",
            "address.city.country",
            "store"
    })
    Page<Staff> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {
            "address",
            "address.city",
            "address.city.country",
            "store"
    })
    Optional<Staff> findByStaffId(Short staffId);


}
