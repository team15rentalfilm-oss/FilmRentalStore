package com.iem.FilmRentalStore.controller;

import com.iem.FilmRentalStore.dto.category.CategoryDTO;
import com.iem.FilmRentalStore.dto.category.CategoryRequestDTO;
import com.iem.FilmRentalStore.dto.category.CategoryResponseDTO;
import com.iem.FilmRentalStore.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryDTO createCategory(
            @Valid @RequestBody CategoryRequestDTO request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable Byte id) {
        return categoryService.getCategoryResponseById(id);
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.getAllCategoryResponses();
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(
            @PathVariable Byte id,
            @Valid @RequestBody CategoryRequestDTO request) {
        return categoryService.updateCategory(id, request);
    }
}
