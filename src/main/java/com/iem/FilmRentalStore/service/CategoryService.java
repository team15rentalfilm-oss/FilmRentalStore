package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.category.CategoryDTO;
import com.iem.FilmRentalStore.dto.category.CategoryRequestDTO;
import com.iem.FilmRentalStore.dto.category.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryRequestDTO request);

    CategoryResponseDTO getCategoryResponseById(Byte id);

    List<CategoryResponseDTO> getAllCategoryResponses();

    CategoryDTO updateCategory(Byte id, CategoryRequestDTO request);
}