package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Byte> {
    // Optional: find by name
    // Optional<Category> findByName(String name);
}
