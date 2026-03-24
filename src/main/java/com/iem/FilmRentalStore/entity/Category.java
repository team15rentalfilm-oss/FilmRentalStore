package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte categoryId;   // TINYINT → Byte

    @Column(length = 25, nullable = false)
    private String name;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    // 🔗 MANY-TO-MANY (inverse side)
    @ManyToMany(mappedBy = "categories")
    private List<Film> films = new ArrayList<>();

    // ================= GETTERS & SETTERS =================

    public Byte getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Byte categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}