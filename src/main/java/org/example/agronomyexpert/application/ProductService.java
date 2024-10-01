package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Product;
import org.example.agronomyexpert.domain.usecase.product.CreateProductUseCase;
import org.example.agronomyexpert.domain.usecase.product.UpdateProductUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateProductDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateProductDto;
import org.example.agronomyexpert.presentation.dto.response.ProductResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class ProductService {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public ProductService(CreateProductUseCase createProductUseCase,
                          UpdateProductUseCase updateProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
    }

    @Transactional
    public ProductResponseDto createProduct(CreateProductDto createProductDto) {
        Product savedProduct = createProductUseCase.createProduct(createProductDto);

        return buildProductResponseDto(savedProduct);
    }

    @Transactional
    public ProductResponseDto updateProduct(Integer id, UpdateProductDto updateProductDto) {
        Product updatedProduct = updateProductUseCase.updateProduct(id, updateProductDto);

        return buildProductResponseDto(updatedProduct);
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
