package com.iem.FilmRentalStore.repository;

import com.iem.FilmRentalStore.entity.FilmCategory;
import com.iem.FilmRentalStore.entity.FilmCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {

    List<FilmCategory> findByFilm_FilmId(Short filmId);

    List<FilmCategory> findByCategory_Id(Byte categoryId);
}