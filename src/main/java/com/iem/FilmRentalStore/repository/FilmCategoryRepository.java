package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.FilmCategory;
import com.iem.FilmRentalStore.entity.FilmCategory.FilmCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {
}