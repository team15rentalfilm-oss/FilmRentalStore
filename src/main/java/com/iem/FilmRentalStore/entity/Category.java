package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte id;

    @Column(name = "name", nullable = false, unique=true, length = 25)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Film> films = new HashSet<>();

    @Column(name = "last_update", nullable = false)
    private java.time.LocalDateTime lastUpdate;

    @PrePersist
    public void prePersist() {
        this.lastUpdate = java.time.LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = java.time.LocalDateTime.now();
    }

    public Category() {}

    public Category(String name) {
        this.name = name;
    }
}
