package com.iem.FilmRentalStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iem.FilmRentalStore.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Byte> {

}