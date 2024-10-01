package org.example.agronomyexpert.domain.usecase.product.impl;

import org.example.agronomyexpert.domain.model.Product;
import org.example.agronomyexpert.domain.usecase.product.ListAllProductsUseCase;
import org.example.agronomyexpert.infrastructure.persistence.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListAllProductsUseCaseImpl implements ListAllProductsUseCase {

    private final ProductRepository productRepository;

    public ListAllProductsUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> listAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
