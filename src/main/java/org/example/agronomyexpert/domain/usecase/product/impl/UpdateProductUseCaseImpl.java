package org.example.agronomyexpert.domain.usecase.product.impl;

import org.example.agronomyexpert.domain.exception.CategoryNotFoundException;
import org.example.agronomyexpert.domain.exception.ProductNotFoundException;
import org.example.agronomyexpert.domain.model.Category;
import org.example.agronomyexpert.domain.model.Product;
import org.example.agronomyexpert.domain.usecase.product.UpdateProductUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CategoryRepository;
import org.example.agronomyexpert.infrastructure.persistence.ProductRepository;
import org.example.agronomyexpert.presentation.dto.request.UpdateProductDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public UpdateProductUseCaseImpl(ProductRepository productRepository,
                                    CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product updateProduct(Integer id, UpdateProductDto updateProductDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Não foi encontrado nenhum produto com o id informado"));

        Optional.ofNullable(updateProductDto.name()).ifPresent(product::setName);
        Optional.ofNullable(updateProductDto.price()).ifPresent(product::setPrice);
        Optional.ofNullable(updateProductDto.categoryId()).ifPresent(newCategoryId -> {
            Category category = categoryRepository.findById(newCategoryId)
                    .orElseThrow(() -> new CategoryNotFoundException("Não foi encontrada nenhuma categoria com o id informado"));
            product.setCategoryFk(category);
        });
        Optional.ofNullable(updateProductDto.photoUrl()).ifPresent(product::setPhotoUrl);

        return productRepository.save(product);
    }
}
