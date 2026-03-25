package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "film_category")
@IdClass(FilmCategory.FilmCategoryId.class)
public class FilmCategory {

    @Id
    @Column(name = "film_id")
    private Short filmId;

    @Id
    @Column(name = "category_id")
    private Byte categoryId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    // Composite key class
    @Getter
    @Setter
    public static class FilmCategoryId implements Serializable {
        private Short filmId;
        private Byte categoryId;

        public FilmCategoryId() {}
        public FilmCategoryId(Short filmId, Byte categoryId) {
            this.filmId = filmId;
            this.categoryId = categoryId;
        }
    }
}