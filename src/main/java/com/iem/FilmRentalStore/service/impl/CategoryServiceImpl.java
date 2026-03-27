package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.entity.Category;
import com.iem.FilmRentalStore.exception.ResourceNotFoundException;
import com.iem.FilmRentalStore.repository.CategoryRepository;
import com.iem.FilmRentalStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        Category saved = categoryRepository.save(category);
        return new CategoryDTO(saved.getId(), saved.getName());
    }

    @Override
    public CategoryDTO getCategoryById(Byte id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return new CategoryDTO(category.getId(), category.getName());
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(cat -> new CategoryDTO(cat.getId(), cat.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Byte id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setName(categoryDTO.getName());
        Category updated = categoryRepository.save(category);
        return new CategoryDTO(updated.getId(), updated.getName());
    }

    @Override
    public void deleteCategory(Byte id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
    }
}
