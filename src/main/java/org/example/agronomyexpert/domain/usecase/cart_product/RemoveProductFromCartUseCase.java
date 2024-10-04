package org.example.agronomyexpert.domain.usecase.cart_product;

import org.example.agronomyexpert.presentation.dto.request.CartProductDto;

public interface RemoveProductFromCartUseCase {
    void removeProductFromCart(CartProductDto cartProductDto);
}
