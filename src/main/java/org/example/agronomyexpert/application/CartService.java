package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Cart;
import org.example.agronomyexpert.domain.usecase.cart.CreateCartUseCase;
import org.example.agronomyexpert.domain.usecase.cart.DeleteCartUseCase;
import org.example.agronomyexpert.domain.usecase.cart.ListAllCartsUseCase;
import org.example.agronomyexpert.presentation.dto.request.CreateCartDto;
import org.example.agronomyexpert.presentation.dto.response.CartResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class CartService {

    private final CreateCartUseCase createCartUseCase;
    private final DeleteCartUseCase deleteCartUseCase;
    private final ListAllCartsUseCase listAllCartsUseCase;

    public CartService(CreateCartUseCase createCartUseCase,
                       DeleteCartUseCase deleteCartUseCase,
                       ListAllCartsUseCase listAllCartsUseCase) {
        this.createCartUseCase = createCartUseCase;
        this.deleteCartUseCase = deleteCartUseCase;
        this.listAllCartsUseCase = listAllCartsUseCase;
    }

    @Transactional(readOnly = true)
    public Page<CartResponseDto> findAll(String requesterUsername, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        return listAllCartsUseCase.listAll(requesterUsername, pageable)
                .map(this::buildCartResponseDto);
    }

    @Transactional
    public CartResponseDto createCart(String requesterUsername, CreateCartDto createCartDto) {
        Cart savedCart = createCartUseCase.createCart(requesterUsername, createCartDto);

        return buildCartResponseDto(savedCart);
    }

    @Transactional
    public CartResponseDto deleteCart(String requesterUsername, Integer cartId) {
        Cart cartWithUpdatedStatus = deleteCartUseCase.deleteCart(requesterUsername, cartId);

        return buildCartResponseDto(cartWithUpdatedStatus);
    }

    private CartResponseDto buildCartResponseDto(Cart cart) {
        return new CartResponseDto(cart.getId(),
                cart.getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                cart.getClientFk().getId(),
                cart.getSellerFk().getId(),
                cart.getTotalValue(),
                cart.getStatus());
    }
}
