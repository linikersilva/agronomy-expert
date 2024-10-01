package org.example.agronomyexpert.domain.usecase.cart;

import org.example.agronomyexpert.domain.model.Cart;

public interface DeleteCartUseCase {

    Cart deleteCart(String requester, Integer cartId);
}
