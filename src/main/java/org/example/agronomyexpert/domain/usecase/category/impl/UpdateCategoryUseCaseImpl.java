package org.example.agronomyexpert.domain.usecase.category.impl;

import org.example.agronomyexpert.domain.exception.CategoryNotFoundException;
import org.example.agronomyexpert.domain.model.Category;
import org.example.agronomyexpert.domain.usecase.category.UpdateCategoryUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CategoryRepository;
import org.example.agronomyexpert.presentation.dto.request.UpdateCategoryDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public UpdateCategoryUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category updateCategory(Integer id, UpdateCategoryDto updateCategoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Não foi encontrada nenhuma categoria com o id informado"));

        Optional.ofNullable(updateCategoryDto.name()).ifPresent(category::setName);

        return categoryRepository.save(category);
    }
}
