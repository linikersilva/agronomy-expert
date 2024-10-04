package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.CartProduct;
import org.example.agronomyexpert.domain.usecase.cart_product.AddProductToCartUseCase;
import org.example.agronomyexpert.domain.usecase.cart_product.RemoveProductFromCartUseCase;
import org.example.agronomyexpert.presentation.dto.request.CartProductDto;
import org.example.agronomyexpert.presentation.dto.response.AddProductToCartResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class CartProductService {

    private final AddProductToCartUseCase addProductToCartUseCase;
    private final RemoveProductFromCartUseCase removeProductFromCartUseCase;

    public CartProductService(AddProductToCartUseCase addProductToCartUseCase,
                              RemoveProductFromCartUseCase removeProductFromCartUseCase) {
        this.addProductToCartUseCase = addProductToCartUseCase;
        this.removeProductFromCartUseCase = removeProductFromCartUseCase;
    }

    @Transactional
    public AddProductToCartResponseDto addProductToCart(CartProductDto cartProductDto) {
        CartProduct cartProduct = addProductToCartUseCase.addProductToCart(cartProductDto);

        return new AddProductToCartResponseDto(cartProduct.getId().getCartId().getId(), cartProduct.getId().getProductId().getId(),
                cartProduct.getQuantity(), cartProduct.getPrice(),
                cartProduct.getTotal(), cartProduct.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    @Transactional
    public void removeProductFromCart(CartProductDto cartProductDto) {
        removeProductFromCartUseCase.removeProductFromCart(cartProductDto);
    }
}
