package com.iem.FilmRentalStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private List<Customer> customers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private List<Staff> staffMembers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private List<Store> stores = new ArrayList<>();
}
