package com.iem.FilmRentalStore.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "language")
@Data
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", columnDefinition = "TINYINT UNSIGNED")
    private Integer languageId;

    @Column(name = "name", columnDefinition = "CHAR(20)")
    private String name;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
