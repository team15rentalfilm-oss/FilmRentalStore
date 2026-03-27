package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.category.CategoryDTO;
import com.iem.FilmRentalStore.dto.category.CategoryRequestDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryRequestDTO request);

    CategoryDTO getCategoryById(Integer id);

    CategoryDTO getCategoryById(Byte id);

    List<CategoryDTO> getAllCategories();

    CategoryDTO updateCategory(Integer id, CategoryRequestDTO request);

    CategoryDTO updateCategory(Byte id, CategoryRequestDTO request);
}