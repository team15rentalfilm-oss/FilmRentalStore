package com.iem.FilmRentalStore.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private Byte storeId;

    @Column(name="last_update")
    private LocalDateTime lastUpdate;

    // Store has one address
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    // Manager (Staff) reference (you will create Staff entity later)
    @Column(name = "manager_staff_id")
    private Byte managerStaffId;

}