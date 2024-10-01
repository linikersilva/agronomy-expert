package org.example.agronomyexpert.presentation.controller;

import jakarta.validation.Valid;
import org.example.agronomyexpert.application.ProductService;
import org.example.agronomyexpert.presentation.dto.request.CreateProductDto;
import org.example.agronomyexpert.presentation.dto.request.UpdateProductDto;
import org.example.agronomyexpert.presentation.dto.response.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid CreateProductDto createProductDto) {
        ProductResponseDto product = productService.createProduct(createProductDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.id()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody @Valid UpdateProductDto updateProductDto,
                                                            @PathVariable Integer productId) {
        ProductResponseDto product = productService.updateProduct(productId, updateProductDto);
        return ResponseEntity.ok().body(product);
    }
}
