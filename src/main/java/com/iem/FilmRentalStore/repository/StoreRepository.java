package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer>, JpaSpecificationExecutor<Store> {

}
