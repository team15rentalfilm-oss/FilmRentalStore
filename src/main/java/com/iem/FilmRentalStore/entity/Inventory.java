package com.iem.FilmRentalStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id", columnDefinition = "MEDIUMINT UNSIGNED")
    private Integer inventoryId;

    @Column(name = "film_id", nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private Integer filmId;

    @Column(name = "store_id", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer storeId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
