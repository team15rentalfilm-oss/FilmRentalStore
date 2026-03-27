package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Short> {

    List<Film> findByTitleContainingIgnoreCase(String title);

    List<Film> findByReleaseYear(Integer releaseYear);

    List<Film> findByTitleContainingIgnoreCaseAndReleaseYear(String title, Integer releaseYear);
}