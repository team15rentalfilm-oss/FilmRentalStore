package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Short> {

    List<Film> findByTitleContainingIgnoreCase(String title);

    List<Film> findByCategories_NameIgnoreCase(String categoryName);
    List<Film> findByTitleContainingIgnoreCaseAndReleaseYear(String title, Integer releaseYear);

    List<Film> findByReleaseYear(Integer releaseYear);

    @EntityGraph(attributePaths = {"categories", "actors", "specialFeatures"})
    Optional<Film> findWithRelationsByFilmId(Short id);

    @EntityGraph(attributePaths = {"categories", "actors", "specialFeatures"})
    Page<Film> findAll(Pageable pageable);
}



