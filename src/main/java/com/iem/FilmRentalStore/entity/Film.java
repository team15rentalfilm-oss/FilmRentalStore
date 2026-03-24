package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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

    @Column(name = "release_year", columnDefinition = "YEAR")
    private Integer releaseYear;

    @Column(name = "rental_duration")
    private Integer rentalDuration;

    @Column(name = "rental_rate", precision = 4, scale = 2)
    private BigDecimal rentalRate;

    private Integer length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    private String rating;

    // 🌐 MANY-TO-ONE → Language
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    // 🔗 MANY-TO-MANY → Category
    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    // 🎭 MANY-TO-MANY → Actor
    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;

    // ================= GETTERS & SETTERS =================

    public Integer getFilmId() { return filmId; }
    public void setFilmId(Integer filmId) { this.filmId = filmId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public Integer getRentalDuration() { return rentalDuration; }
    public void setRentalDuration(Integer rentalDuration) { this.rentalDuration = rentalDuration; }

    public BigDecimal getRentalRate() { return rentalRate; }
    public void setRentalRate(BigDecimal rentalRate) { this.rentalRate = rentalRate; }

    public Integer getLength() { return length; }
    public void setLength(Integer length) { this.length = length; }

    public BigDecimal getReplacementCost() { return replacementCost; }
    public void setReplacementCost(BigDecimal replacementCost) { this.replacementCost = replacementCost; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }

    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories) { this.categories = categories; }

    public List<Actor> getActors() { return actors; }
    public void setActors(List<Actor> actors) { this.actors = actors; }
}