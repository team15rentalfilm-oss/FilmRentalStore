package com.iem.FilmRentalStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "film_category")
@IdClass(FilmCategory.FilmCategoryId.class)
@Getter
@Setter
public class FilmCategory {

    @Id
    @Column(name = "film_id")
    private Short filmId;

    @Id
    @Column(name = "category_id")
    private Byte categoryId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", insertable = false, updatable = false)
    private Film film;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category category;

    @Getter
    @Setter
    @EqualsAndHashCode
    public static class FilmCategoryId implements Serializable {
        private Short filmId;
        private Byte categoryId;

        public FilmCategoryId() {
        }

        public FilmCategoryId(Short filmId, Byte categoryId) {
            this.filmId = filmId;
            this.categoryId = categoryId;
        }
    }
}
