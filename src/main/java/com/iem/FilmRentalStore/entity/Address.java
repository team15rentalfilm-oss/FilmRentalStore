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
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Short addressId;

    @Column(name="address")
    private String address;
    @Column(name="address2")
    private String address2;
    @Column(name="district")
    private String district;
    @Column(name="postal_code")
    private String postalCode;
    @Column(name="phone")
    private String phone;

    @Column(name="last_update")
    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
