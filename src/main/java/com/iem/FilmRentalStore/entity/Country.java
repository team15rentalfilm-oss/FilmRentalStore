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
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", columnDefinition = "SMALLINT UNSIGNED")
    private Short countryId;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @JsonIgnore
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<City> cities = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}
