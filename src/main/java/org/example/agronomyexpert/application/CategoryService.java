package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Category;
import org.example.agronomyexpert.domain.usecase.category.CreateCategoryUseCase;
import org.example.agronomyexpert.domain.usecase.category.ListAllCategoriesUseCase;
import org.example.agronomyexpert.domain.usecase.category.UpdateCategoryUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateCategoryDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateCategoryDto;
import org.example.agronomyexpert.presentation.dto.response.CategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class CategoryService {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final ListAllCategoriesUseCase listAllCategoriesUseCase;

    public CategoryService(CreateCategoryUseCase createCategoryUseCase,
                           UpdateCategoryUseCase updateCategoryUseCase,
                           ListAllCategoriesUseCase listAllCategoriesUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.listAllCategoriesUseCase = listAllCategoriesUseCase;
    }

    @Transactional(readOnly = true)
    public Page<CategoryResponseDto> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        return listAllCategoriesUseCase.listAll(pageable)
                .map(this::buildCategoryResponseDto);
    }

    @Transactional
    public CategoryResponseDto createCategory(CreateCategoryDto createCategoryDto) {
        Category savedCategory = createCategoryUseCase.createCategory(createCategoryDto);

        return buildCategoryResponseDto(savedCategory);
    }

    @Transactional
    public CategoryResponseDto updateCategory(Integer id, UpdateCategoryDto updateCategoryDto) {
        Category updatedCategory = updateCategoryUseCase.updateCategory(id, updateCategoryDto);

        return buildCategoryResponseDto(updatedCategory);
    }

    private CategoryResponseDto buildCategoryResponseDto(Category category) {
        return new CategoryResponseDto(category.getId(),
                category.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                category.getName());
    }
}
