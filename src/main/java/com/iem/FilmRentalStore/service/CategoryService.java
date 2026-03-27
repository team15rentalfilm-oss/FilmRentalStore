package com.iem.FilmRentalStore.service;

import com.iem.FilmRentalStore.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Byte id);

    List<CategoryDTO> getAllCategories();

    CategoryDTO updateCategory(Byte id, CategoryDTO categoryDTO);

    void deleteCategory(Byte id);
}
