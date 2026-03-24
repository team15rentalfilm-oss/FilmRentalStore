package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Short customerId;

    @Column(name = "store_id")
    private Byte storeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "address_id")
    private Short addressId;

    private Boolean active;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @PrePersist
    public void prePersist() {
        if (createDate == null) {
            createDate = LocalDateTime.now();
        }
        lastUpdate = LocalDateTime.now();
        if (active == null) {
            active = true;
        }
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}