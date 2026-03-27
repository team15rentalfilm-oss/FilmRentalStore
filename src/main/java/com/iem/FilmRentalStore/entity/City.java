package com.iem.FilmRentalStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Short cityId;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    // 🔥 FIXED: Proper mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @JsonIgnore
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}