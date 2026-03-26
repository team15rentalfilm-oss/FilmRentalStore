package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", columnDefinition = "TINYINT UNSIGNED")
    private Integer storeId;

    @ManyToOne
    @JoinColumn(name = "manager_staff_id", nullable = false)
    private Staff manager;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @PrePersist
    public void prePersist() {
        this.lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }
}