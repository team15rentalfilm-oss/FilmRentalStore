package com.iem.FilmRentalStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "rental",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"rental_date", "inventory_id", "customer_id"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id", columnDefinition = "INT")
    private Integer rentalId;

    @Column(name = "rental_date", nullable = false)
    private LocalDateTime rentalDate;

    @Column(name = "inventory_id", nullable = false, columnDefinition = "MEDIUMINT UNSIGNED")
    private Integer inventoryId;

    @Column(name = "customer_id", nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private Short customerId;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "staff_id", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer staffId;

    @Column(name = "last_update", insertable = false, updatable = false)
    private LocalDateTime lastUpdate;
}
