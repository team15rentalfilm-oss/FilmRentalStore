package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Byte> {
    Optional<Category> findByNameIgnoreCase(String name);
}
