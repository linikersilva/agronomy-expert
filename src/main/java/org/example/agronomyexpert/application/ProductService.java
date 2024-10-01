package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Product;
import org.example.agronomyexpert.domain.usecase.product.CreateProductUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateProductDto;
import org.example.agronomyexpert.presentation.dto.response.ProductResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class ProductService {

    private final CreateProductUseCase createProductUseCase;

    public ProductService(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @Transactional
    public ProductResponseDto createProduct(CreateProductDto createProductDto) {
        Product savedProduct = createProductUseCase.createProduct(createProductDto);

        return buildProductResponseDto(savedProduct);
    }

    private ProductResponseDto buildProductResponseDto(Product product) {
        return new ProductResponseDto(product.getId(),
                product.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategoryFk().getId(),
                product.getPhotoUrl());
    }
}
