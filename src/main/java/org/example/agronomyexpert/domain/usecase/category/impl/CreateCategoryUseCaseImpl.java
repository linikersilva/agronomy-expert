package org.example.agronomyexpert.domain.usecase.category.impl;

import org.example.agronomyexpert.domain.model.Category;
import org.example.agronomyexpert.domain.usecase.category.CreateCategoryUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CategoryRepository;
import org.example.agronomyexpert.presentation.dto.request.CreateCategoryDto;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CreateCategoryDto createCategoryDto) {
        Category category = Category.create(createCategoryDto);
        return categoryRepository.save(category);
    }
}
