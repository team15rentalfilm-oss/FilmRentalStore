package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.FilmCategoryDTO;

import java.util.List;

public interface FilmCategoryService {

    List<FilmCategoryDTO> getAll();

    FilmCategoryDTO create(FilmCategoryDTO dto);

    void delete(Short filmId, Byte categoryId);
}