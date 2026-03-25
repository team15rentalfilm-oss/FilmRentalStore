package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private Short Id;

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

    @ManyToMany
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;
}
