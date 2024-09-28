package org.example.agronomyexpert.domain.usecase.category;

import org.example.agronomyexpert.domain.model.Category;
import org.example.agronomyexpert.presentation.dto.request.CreateCategoryDto;

public interface CreateCategoryUseCase {
    Category createCategory(CreateCategoryDto createCategoryDto);
}
