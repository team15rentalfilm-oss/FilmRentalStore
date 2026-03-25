package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Short> {
    // Optional: custom queries here if needed
    // Example: find all films by category name
    // List<Film> findByCategoriesName(String categoryName);
}
