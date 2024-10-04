package org.example.agronomyexpert.domain.usecase.cart_product;

import org.example.agronomyexpert.domain.model.CartProduct;
import org.example.agronomyexpert.presentation.dto.request.CartProductDto;

public interface AddProductToCartUseCase {
    CartProduct addProductToCart(CartProductDto cartProductDto);
}
