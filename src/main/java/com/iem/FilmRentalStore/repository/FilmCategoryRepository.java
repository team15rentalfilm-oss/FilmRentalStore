package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.FilmCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategory.FilmCategoryId> {
}
