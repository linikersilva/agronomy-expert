package org.example.agronomyexpert.domain.usecase.category;

import org.example.agronomyexpert.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListAllCategoriesUseCase {
    Page<Category> listAll(Pageable pageable);
}
