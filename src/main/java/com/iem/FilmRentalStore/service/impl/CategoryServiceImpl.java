package com.iem.FilmRentalStore.service.impl;

import com.iem.FilmRentalStore.dto.category.CategoryDTO;
import com.iem.FilmRentalStore.dto.category.CategoryRequestDTO;
import com.iem.FilmRentalStore.dto.category.CategoryResponseDTO;
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

        String normalizedName = request.getName().trim();

        categoryRepository.findByNameIgnoreCase(normalizedName)
                .ifPresent(existing -> {
                    throw new IllegalArgumentException(
                            "Category already exists with name: " + normalizedName
                    );
                });

        Category category = new Category();
        category.setName(normalizedName);

        Category saved = categoryRepository.save(category);

        return categoryMapper.toDTO(saved);
    }

    @Override
    public CategoryResponseDTO getCategoryResponseById(Byte id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));

        return CategoryMapper.toResponseDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategoryResponses() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toResponseDTO)
                .toList();
    }

    @Override
    public CategoryDTO updateCategory(Byte id, CategoryRequestDTO request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Category not found with id: " + id));

        String normalizedName = request.getName().trim();

        // 🔴 Duplicate check (exclude current category)
        categoryRepository.findByNameIgnoreCase(normalizedName)
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new IllegalArgumentException(
                            "Category already exists with name: " + normalizedName
                    );
                });

        category.setName(normalizedName);

        Category updated = categoryRepository.save(category);

        return categoryMapper.toDTO(updated);
    }
}