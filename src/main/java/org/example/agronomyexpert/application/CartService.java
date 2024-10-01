package org.example.agronomyexpert.application;

import org.example.agronomyexpert.domain.model.Cart;
import org.example.agronomyexpert.domain.usecase.cart.CreateCartUseCase;
import org.example.agronomyexpert.infrastructure.persistence.ClientRepository;
import org.example.agronomyexpert.presentation.dto.request.CreateCartDto;
import org.example.agronomyexpert.presentation.dto.response.CartResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

@Service
public class CartService {

    private final CreateCartUseCase createCartUseCase;
    private final ClientRepository clientRepository;

    public CartService(CreateCartUseCase createCartUseCase,
                       ClientRepository clientRepository) {
        this.createCartUseCase = createCartUseCase;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public CartResponseDto createCart(String requesterUsername, CreateCartDto createCartDto) {
        Cart savedCart = createCartUseCase.createCart(requesterUsername, createCartDto);

        return buildCartResponseDto(savedCart);
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
