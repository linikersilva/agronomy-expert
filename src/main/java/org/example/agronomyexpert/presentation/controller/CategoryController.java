package org.example.agronomyexpert.presentation.controller;

import jakarta.validation.Valid;
import org.example.agronomyexpert.application.CategoryService;
import org.example.agronomyexpert.presentation.dto.request.CreateCategoryDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateCategoryDto;
import org.example.agronomyexpert.presentation.dto.response.CategoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryResponseDto>> findAll(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer size) {
        Page<CategoryResponseDto> response = categoryService.findAll(page, size);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody @Valid CreateCategoryDto createCategoryDto) {
        CategoryResponseDto category = categoryService.createCategory(createCategoryDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(category.id()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody @Valid UpdateCategoryDto updateCategoryDto,
                                                              @PathVariable Integer categoryId) {
        CategoryResponseDto category = categoryService.updateCategory(categoryId, updateCategoryDto);
        return ResponseEntity.ok().body(category);
    }
}
