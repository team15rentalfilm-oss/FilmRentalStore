package com.iem.FilmRentalStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "language")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", columnDefinition = "TINYINT UNSIGNED")
    private Integer languageId;

    @Column(name = "name", columnDefinition = "CHAR(20)")
    private String name;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @JsonIgnore
    @OneToMany(mappedBy = "language")
    private List<Film> films = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "originalLanguage")
    private List<Film> originalLanguageFilms = new ArrayList<>();
}
