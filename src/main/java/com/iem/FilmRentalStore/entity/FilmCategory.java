package com.iem.FilmRentalStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "film_category")
@IdClass(FilmCategory.FilmCategoryId.class)
@Getter
@Setter
@NoArgsConstructor
public class FilmCategory {

    @Id
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private Short filmId;

    @Id
    @Column(name = "category_id", columnDefinition = "TINYINT UNSIGNED")
    private Byte categoryId;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "film_id", insertable = false, updatable = false)
    private Film film;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    private Category category;

    @PrePersist
    public void prePersist() {
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class FilmCategoryId implements Serializable {
        private Short filmId;
        private Byte categoryId;

        public FilmCategoryId(Short filmId, Byte categoryId) {
            this.filmId = filmId;
            this.categoryId = categoryId;
        }
    }
}