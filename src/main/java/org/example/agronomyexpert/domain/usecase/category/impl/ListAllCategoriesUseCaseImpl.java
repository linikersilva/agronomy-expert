package org.example.agronomyexpert.domain.usecase.category.impl;

import org.example.agronomyexpert.domain.model.Category;
import org.example.agronomyexpert.domain.usecase.category.ListAllCategoriesUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAllCategoriesUseCaseImpl implements ListAllCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public ListAllCategoriesUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> listAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
