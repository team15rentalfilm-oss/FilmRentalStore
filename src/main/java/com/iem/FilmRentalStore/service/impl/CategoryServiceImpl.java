package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.category.CategoryDTO;
import com.iem.FilmRentalStore.dto.category.CategoryRequestDTO;
import com.iem.FilmRentalStore.entity.Category;
import com.iem.FilmRentalStore.mapper.CategoryMapper;
import com.iem.FilmRentalStore.repository.CategoryRepository;
import com.iem.FilmRentalStore.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO createCategory(CategoryRequestDTO request) {
        Category category = CategoryMapper.toEntity(request);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toDTO(saved);
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        return null;
    }

    @Override
    public CategoryDTO getCategoryById(Byte id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        return categoryMapper.toDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    @Override
    public CategoryDTO updateCategory(Integer id, CategoryRequestDTO request) {
        return null;
    }

    @Override
    public CategoryDTO updateCategory(Byte id, CategoryRequestDTO request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        category.setName(request.getName());

        Category updated = categoryRepository.save(category);
        return categoryMapper.toDTO(updated);
    }
}