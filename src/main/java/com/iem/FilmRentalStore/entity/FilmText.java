package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "film_text")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmText {

    @Id
    @Column(name = "film_id")
    private Integer filmId;

    // One-to-One with Film (shared PK)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description")
    private String description;
}
