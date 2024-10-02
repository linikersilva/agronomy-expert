package org.example.agronomyexpert.domain.usecase.cart;

import org.example.agronomyexpert.domain.model.Cart;

public interface ResumeCartUseCase {

    Cart resumeCart(String requesterUsername, Integer cartId);
}
