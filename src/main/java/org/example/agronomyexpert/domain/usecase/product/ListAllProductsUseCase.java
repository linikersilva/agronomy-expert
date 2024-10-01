package org.example.agronomyexpert.domain.usecase.product;

import org.example.agronomyexpert.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListAllProductsUseCase {
    Page<Product> listAll(Pageable pageable);
}
