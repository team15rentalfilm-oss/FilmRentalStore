package com.iem.FilmRentalStore.mapper;
import com.iem.FilmRentalStore.dto.category.*;

import com.iem.FilmRentalStore.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    //RequestDTO → Entity
    public static Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }

    //Entity → ResponseDTO
    public static CategoryResponseDTO toResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setCategoryId(category.getId());
        dto.setName(category.getName());
        dto.setLastUpdate(category.getLastUpdate());
        return dto;
    }

    //Entity → Lightweight DTO
    public static CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setName(category.getName());
        return dto;
    }
}