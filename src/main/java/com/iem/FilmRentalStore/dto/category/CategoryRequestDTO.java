package com.iem.FilmRentalStore.dto.category;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {

    @NotBlank(message = "Category name is required")
    @Size(max = 25, message = "Category name must be at most 25 characters")
    private String name;
}
