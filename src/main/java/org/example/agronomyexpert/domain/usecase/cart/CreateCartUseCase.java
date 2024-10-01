package org.example.agronomyexpert.domain.usecase.cart;

import org.example.agronomyexpert.domain.model.Cart;
import org.example.agronomyexpert.presentation.dto.request.CreateCartDto;

public interface CreateCartUseCase {

    Cart createCart(String requester, CreateCartDto createCartDto);
}
