package com.iem.FilmRentalStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", columnDefinition = "TINYINT UNSIGNED")
    private Byte storeId;

    @Column(name = "manager_staff_id", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Byte managerStaffId;

    @Column(name = "address_id", nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private Short addressId;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_staff_id", insertable = false, updatable = false)
    private Staff managerStaff;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Customer> customers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Staff> staffMembers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Inventory> inventories = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}