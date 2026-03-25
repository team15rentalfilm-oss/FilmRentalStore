package com.iem.FilmRentalStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Byte id;

    @NotBlank(message = "Category name is mandatory")
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    private String name;
}
