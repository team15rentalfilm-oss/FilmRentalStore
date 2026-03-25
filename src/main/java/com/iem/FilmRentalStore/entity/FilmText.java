package com.iem.FilmRentalStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "film_text")
@Data
public class FilmText {

    @Id
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer filmId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
