package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"categories", "filmActors", "filmCategories"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    @EqualsAndHashCode.Include
    private Short filmId;

    @Column(nullable = false, length = 128)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "release_year", columnDefinition = "YEAR")
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(name = "rental_duration")
    private Integer rentalDuration;

    @Column(name = "rental_rate", precision = 4, scale = 2)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost", precision = 5, scale = 2)
    private BigDecimal replacementCost;

    @Column(name = "rating")
    private String rating;

    @ElementCollection
    @CollectionTable(name = "film_special_features", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "feature")
    private Set<String> specialFeatures = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new java.util.HashSet<>();

    @OneToMany(mappedBy = "film", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<FilmActor> filmActors = new LinkedHashSet<>();

    @Column(name = "last_update")
    private java.time.LocalDateTime lastUpdate;

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.lastUpdate = java.time.LocalDateTime.now();
    }

    @OneToMany(mappedBy = "film")
    private Set<FilmCategory> filmCategories;

    @Transient
    public Set<Actor> getActors() {
        return filmActors.stream()
                .map(FilmActor::getActor)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void setActors(Set<Actor> actors) {
        for (FilmActor filmActor : new LinkedHashSet<>(filmActors)) {
            removeActorLink(filmActor);
        }

        if (actors == null) {
            return;
        }

        actors.forEach(this::addActor);
    }

    public void addActor(Actor actor) {
        if (actor == null) {
            return;
        }

        boolean alreadyLinked = filmActors.stream()
                .anyMatch(filmActor -> actor.equals(filmActor.getActor()));

        if (alreadyLinked) {
            return;
        }

        FilmActor filmActor = new FilmActor(this, actor);
        filmActors.add(filmActor);
        actor.getFilmActors().add(filmActor);
    }

    private void removeActorLink(FilmActor filmActor) {
        filmActors.remove(filmActor);
        if (filmActor.getActor() != null) {
            filmActor.getActor().getFilmActors().remove(filmActor);
        }
        filmActor.setFilm(null);
        filmActor.setActor(null);
    }
}
