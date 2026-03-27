package com.iem.FilmRentalStore.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryResponseDTO {

    private Integer categoryId;
    private String name;
    private LocalDateTime lastUpdate;
}