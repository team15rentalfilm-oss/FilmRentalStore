package com.iem.FilmRentalStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "film_text")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmText {

    @Id
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED", nullable = false)
    private Short filmId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}