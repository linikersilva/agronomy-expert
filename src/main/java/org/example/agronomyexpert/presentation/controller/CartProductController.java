package org.example.agronomyexpert.presentation.controller;

import jakarta.validation.Valid;
import org.example.agronomyexpert.application.CartProductService;
import org.example.agronomyexpert.presentation.dto.request.CartProductDto;
import org.example.agronomyexpert.presentation.dto.response.AddProductToCartResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cart-product")
public class CartProductController {

    private final CartProductService cartProductService;

    @Autowired
    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @PostMapping("/add")
    public ResponseEntity<AddProductToCartResponseDto> addProductToCart(@RequestBody @Valid CartProductDto cartProductDto) {
        AddProductToCartResponseDto addProductToCartResponseDto = cartProductService.addProductToCart(cartProductDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(addProductToCartResponseDto.cartId()).toUri();
        return ResponseEntity.created(uri).body(addProductToCartResponseDto);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeProductFromCart(@RequestBody @Valid CartProductDto cartProductDto) {
        cartProductService.removeProductFromCart(cartProductDto);
        return ResponseEntity.noContent().build();
    }
}
