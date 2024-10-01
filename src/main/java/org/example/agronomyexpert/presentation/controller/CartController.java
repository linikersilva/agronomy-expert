package org.example.agronomyexpert.presentation.controller;

import jakarta.validation.Valid;
import org.example.agronomyexpert.application.CartService;
import org.example.agronomyexpert.presentation.dto.request.CreateCartDto;
import org.example.agronomyexpert.presentation.dto.response.CartResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartResponseDto> createCart(@AuthenticationPrincipal String userDetails,
                                                      @RequestBody @Valid CreateCartDto createCartDto) {
        CartResponseDto cart = cartService.createCart(userDetails, createCartDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cart.id()).toUri();
        return ResponseEntity.created(uri).body(cart);
    }

    @PatchMapping("/{cartId}")
    public ResponseEntity<CartResponseDto> deleteCart(@AuthenticationPrincipal String userDetails,
                                                      @PathVariable Integer cartId) {
        CartResponseDto cart = cartService.deleteCart(userDetails, cartId);
        return ResponseEntity.ok().body(cart);
    }
}
