package com.iem.FilmRentalStore.entity;

import java.time.LocalDateTime;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short addressId;

    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;

    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
