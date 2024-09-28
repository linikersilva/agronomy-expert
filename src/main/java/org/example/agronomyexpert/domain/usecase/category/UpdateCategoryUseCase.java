package org.example.agronomyexpert.domain.usecase.category;

import org.example.agronomyexpert.domain.model.Category;
import org.example.agronomyexpert.presentation.dto.request.UpdateCategoryDto;

public interface UpdateCategoryUseCase {
    Category updateCategory(Integer id, UpdateCategoryDto updateCategoryDto);
}
