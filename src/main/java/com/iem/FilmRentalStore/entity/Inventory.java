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
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", columnDefinition = "MEDIUMINT UNSIGNED")
    private Integer inventoryId;

    @Column(name = "film_id", nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private Short filmId;

    @Column(name = "store_id", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Byte storeId;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    private Film film;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    @JsonIgnore
    @OneToMany(mappedBy = "inventory", fetch = FetchType.LAZY)
    private List<Rental> rentals = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}