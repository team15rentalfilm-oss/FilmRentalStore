package com.iem.FilmRentalStore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", columnDefinition = "SMALLINT UNSIGNED")
    private Short paymentId;

    @Column(name = "customer_id", nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private Short customerId;

    @Column(name = "staff_id", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer staffId;

    @Column(name = "rental_id", columnDefinition = "INT")
    private Integer rentalId;

    @Column(name = "amount", nullable = false, precision = 5, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "last_update", insertable = false, updatable = false)
    private LocalDateTime lastUpdate;
}
