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

    @Column(name = "manager_staff_id", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer managerStaffId;

    @Column(name = "address_id", nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private Integer addressId;

    @Column(name = "last_update", insertable = false, updatable = false)
    private LocalDateTime lastUpdate;
}
