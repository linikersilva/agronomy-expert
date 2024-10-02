package org.example.agronomyexpert.presentation.controller;

import jakarta.validation.Valid;
import org.example.agronomyexpert.application.CartService;
import org.example.agronomyexpert.presentation.dto.request.CreateCartDto;
import org.example.agronomyexpert.presentation.dto.response.CartResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/resume-cart/{cartId}")
    public ResponseEntity<CartResponseDto> resumeCart(@AuthenticationPrincipal String userDetails,
                                                      @PathVariable Integer cartId) {
        CartResponseDto cart = cartService.resumeCart(userDetails, cartId);
        return ResponseEntity.ok().body(cart);
    }

    @GetMapping
    public ResponseEntity<Page<CartResponseDto>> findAll(@AuthenticationPrincipal String userDetails,
                                                         @RequestParam(defaultValue = "0") Integer page,
                                                         @RequestParam(defaultValue = "10") Integer size) {
        Page<CartResponseDto> response = cartService.findAll(userDetails, page, size);
        return ResponseEntity.ok().body(response);
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
