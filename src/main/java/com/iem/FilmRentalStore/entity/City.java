package com.iem.FilmRentalStore.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short cityId;

    private String city;

    private LocalDateTime lastUpdate;

    // Many cities belong to one country
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    // One city has many addresses
    @OneToMany(mappedBy = "city")
    private List<Address> addresses;

}
