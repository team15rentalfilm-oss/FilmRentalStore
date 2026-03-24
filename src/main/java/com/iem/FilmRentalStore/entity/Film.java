package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer filmId;

    @Column(nullable = false, length = 128)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    // MySQL 'YEAR' type maps best to Integer in Java 
    // but we must tell Hibernate the column type
    @Column(name = "release_year", columnDefinition = "YEAR")
    private Integer releaseYear;

    @Column(name = "rental_rate", precision = 4, scale = 2)
    private BigDecimal rentalRate;

    // Explicit Getters
    public Integer getFilmId() { return filmId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Integer getReleaseYear() { return releaseYear; }
    public BigDecimal getRentalRate() { return rentalRate; }

    // Explicit Setters
    public void setFilmId(Integer filmId) { this.filmId = filmId; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }
    public void setRentalRate(BigDecimal rentalRate) { this.rentalRate = rentalRate; }
}