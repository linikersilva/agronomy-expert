package org.example.agronomyexpert.domain.usecase.product;

import org.example.agronomyexpert.domain.model.Product;
import org.example.agronomyexpert.presentation.dto.request.CreateProductDto;

public interface CreateProductUseCase {
    Product createProduct(CreateProductDto createProductDto);
}
