package com.iem.FilmRentalStore.dto;

public class FilmCategoryDTO {

    public Short filmId;
    public Byte categoryId;

    public FilmCategoryDTO() {}

    public FilmCategoryDTO(Short filmId, Byte categoryId) {
        this.filmId = filmId;
        this.categoryId = categoryId;
    }
}