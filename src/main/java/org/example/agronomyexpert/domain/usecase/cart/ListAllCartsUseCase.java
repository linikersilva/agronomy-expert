package org.example.agronomyexpert.domain.usecase.cart;

import org.example.agronomyexpert.domain.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListAllCartsUseCase {
    Page<Cart> listAll(String requesterUsername, Pageable pageable);
}
