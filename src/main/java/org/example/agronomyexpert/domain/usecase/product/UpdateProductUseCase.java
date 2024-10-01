package org.example.agronomyexpert.domain.usecase.product;

import org.example.agronomyexpert.domain.model.Product;
import org.example.agronomyexpert.presentation.dto.request.UpdateProductDto;

public interface UpdateProductUseCase {

    Product updateProduct(Integer id, UpdateProductDto updateProductDto);
}
