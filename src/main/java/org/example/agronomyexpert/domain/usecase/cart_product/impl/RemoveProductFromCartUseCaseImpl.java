package org.example.agronomyexpert.domain.usecase.cart_product.impl;

import org.example.agronomyexpert.domain.exception.ProductNotFoundException;
import org.example.agronomyexpert.domain.exception.ProductNotFoundInCartException;
import org.example.agronomyexpert.domain.model.CartProduct;
import org.example.agronomyexpert.domain.model.Product;
import org.example.agronomyexpert.domain.model.Stock;
import org.example.agronomyexpert.domain.model.enums.StockOperationTypeEnum;
import org.example.agronomyexpert.domain.usecase.cart_product.RemoveProductFromCartUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CartProductRepository;
import org.example.agronomyexpert.infrastructure.persistence.ProductRepository;
import org.example.agronomyexpert.infrastructure.persistence.StockRepository;
import org.example.agronomyexpert.presentation.dto.request.CartProductDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RemoveProductFromCartUseCaseImpl implements RemoveProductFromCartUseCase {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final CartProductRepository cartProductRepository;

    public RemoveProductFromCartUseCaseImpl(ProductRepository productRepository,
                                            StockRepository stockRepository,
                                            CartProductRepository cartProductRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public void removeProductFromCart(CartProductDto cartProductDto) {
        CartProduct cartProduct = cartProductRepository.findById_CartId_IdAndId_ProductId_Id(cartProductDto.cartId(),
                                                                                             cartProductDto.productId())
                .orElseThrow(() -> new ProductNotFoundInCartException("O produto informado não foi encontrado no carrinho"));

        Product product = productRepository.findById(cartProductDto.productId())
                .orElseThrow(() -> new ProductNotFoundException("Não foi encontrado nenhum produto com o id informado"));

        if (cartProduct.getQuantity() - cartProductDto.quantity() <= 0) {
            stockRepository.save(Stock.create(product, cartProduct.getQuantity(), StockOperationTypeEnum.ENTRADA));
            productRepository.increaseProductQuantity(cartProduct.getQuantity(), cartProductDto.productId());
            cartProductRepository.delete(cartProduct);
        } else {
            cartProduct.setQuantity(cartProduct.getQuantity() - cartProductDto.quantity());
            cartProduct.setTotal(cartProduct.getTotal().subtract(cartProduct.getPrice().multiply(BigDecimal.valueOf(cartProductDto.quantity()))));
            stockRepository.save(Stock.create(product, cartProductDto.quantity(), StockOperationTypeEnum.ENTRADA));
            productRepository.increaseProductQuantity(cartProductDto.quantity(), cartProductDto.productId());
            cartProductRepository.save(cartProduct);
        }
    }
}
