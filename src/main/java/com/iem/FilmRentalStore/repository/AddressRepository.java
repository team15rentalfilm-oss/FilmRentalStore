package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Address;
import com.iem.FilmRentalStore.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Short> {

    @Query(
            value = "SELECT a FROM Address a JOIN a.city c JOIN c.country",
            countQuery = "SELECT COUNT(a) FROM Address a"
    )
    Page<Address> findAllWithFetch(Pageable pageable);

    @Query(
            value = "SELECT a FROM Address a JOIN a.city c JOIN c.country " +
                    "WHERE LOWER(a.address) LIKE LOWER(CONCAT('%', :address, '%'))",
            countQuery = "SELECT COUNT(a) FROM Address a WHERE LOWER(a.address) LIKE LOWER(CONCAT('%', :address, '%'))"
    )
    Page<Address> findByAddressWithFetch(String address, Pageable pageable);

    @Query(
            value = "SELECT a FROM Address a JOIN a.city c JOIN c.country " +
                    "WHERE LOWER(a.district) LIKE LOWER(CONCAT('%', :district, '%'))",
            countQuery = "SELECT COUNT(a) FROM Address a WHERE LOWER(a.district) LIKE LOWER(CONCAT('%', :district, '%'))"
    )
    Page<Address> findByDistrictWithFetch(String district, Pageable pageable);

    @Query(
            value = "SELECT a FROM Address a JOIN a.city c JOIN c.country " +
                    "WHERE LOWER(c.city) LIKE LOWER(CONCAT('%', :city, '%'))",
            countQuery = "SELECT COUNT(a) FROM Address a JOIN a.city c WHERE LOWER(c.city) LIKE LOWER(CONCAT('%', :city, '%'))"
    )
    Page<Address> findByCityWithFetch(String city, Pageable pageable);

    @Query(
            value = "SELECT a FROM Address a JOIN a.city c JOIN c.country " +
                    "WHERE LOWER(c.country.country) LIKE LOWER(CONCAT('%', :country, '%'))",
            countQuery = "SELECT COUNT(a) FROM Address a JOIN a.city c JOIN c.country " +
                    "WHERE LOWER(c.country.country) LIKE LOWER(CONCAT('%', :country, '%'))"
    )
    Page<Address> findByCountryWithFetch(String country, Pageable pageable);

    Optional<Address> findByAddressAndCity(String address, City city);

    @Query("SELECT a FROM Address a JOIN FETCH a.city c JOIN FETCH c.country WHERE a.addressId = :id")
    Optional<Address> findByIdWithFetch(Short id);
}
