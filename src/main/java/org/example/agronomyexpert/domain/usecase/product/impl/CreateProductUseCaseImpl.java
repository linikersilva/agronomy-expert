package org.example.agronomyexpert.domain.usecase.product.impl;

import org.example.agronomyexpert.domain.exception.CategoryNotFoundException;
import org.example.agronomyexpert.domain.exception.ProductNotFoundException;
import org.example.agronomyexpert.domain.model.Category;
import org.example.agronomyexpert.domain.model.Product;
import org.example.agronomyexpert.domain.usecase.product.CreateProductUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CategoryRepository;
import org.example.agronomyexpert.infrastructure.persistence.ProductRepository;
import org.example.agronomyexpert.presentation.dto.request.CreateProductDto;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public CreateProductUseCaseImpl(ProductRepository productRepository,
                                    CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductDto createProductDto) {
        Category category = categoryRepository.findById(createProductDto.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Não foi encontrada nenhuma categoria com o id informado"));

        Product product = Product.create(createProductDto, category);
        return productRepository.save(product);
    }
}
