package org.example.agronomyexpert.domain.usecase.cart_product.impl;

import org.example.agronomyexpert.domain.exception.CartNotFoundException;
import org.example.agronomyexpert.domain.exception.ProductNotFoundException;
import org.example.agronomyexpert.domain.model.Cart;
import org.example.agronomyexpert.domain.model.CartProduct;
import org.example.agronomyexpert.domain.model.Product;
import org.example.agronomyexpert.domain.model.Stock;
import org.example.agronomyexpert.domain.model.enums.StockOperationTypeEnum;
import org.example.agronomyexpert.domain.usecase.cart_product.AddProductToCartUseCase;
import org.example.agronomyexpert.infrastructure.persistence.CartProductRepository;
import org.example.agronomyexpert.infrastructure.persistence.CartRepository;
import org.example.agronomyexpert.infrastructure.persistence.ProductRepository;
import org.example.agronomyexpert.infrastructure.persistence.StockRepository;
import org.example.agronomyexpert.presentation.dto.request.CartProductDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AddProductToCartUseCaseImpl implements AddProductToCartUseCase {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final CartProductRepository cartProductRepository;

    public AddProductToCartUseCaseImpl(CartRepository cartRepository,
                                       ProductRepository productRepository,
                                       StockRepository stockRepository,
                                       CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public CartProduct addProductToCart(CartProductDto cartProductDto) {
        Cart cart = cartRepository.findById(cartProductDto.cartId())
                .orElseThrow(() -> new CartNotFoundException("Não foi encontrado nenhum carrinho com o id informado"));

        Product product = productRepository.findById(cartProductDto.productId())
                .orElseThrow(() -> new ProductNotFoundException("Não foi encontrado nenhum produto com o id informado"));

        Optional<CartProduct> cartProductOptional = cartProductRepository.findById_CartId_IdAndId_ProductId_Id(cartProductDto.cartId(),
                cartProductDto.productId());

        CartProduct cartProduct;
        if (cartProductOptional.isPresent()) {
            CartProduct existentCartProduct = cartProductOptional.get();

            if (cartProductDto.quantity() > product.getStockQuantity()) {
                throw new IllegalArgumentException("Estoque do produto é insuficiente");
            }

            existentCartProduct.setQuantity(existentCartProduct.getQuantity() + cartProductDto.quantity());

            BigDecimal totalMultiplicationResult = product.getPrice().multiply(BigDecimal.valueOf(cartProductDto.quantity()));
            existentCartProduct.setTotal(existentCartProduct.getTotal().add(totalMultiplicationResult));

            cartProduct = existentCartProduct;
        } else {
            cartProduct = CartProduct.create(cart, product, cartProductDto.quantity(), product.getPrice());
        }

        stockRepository.save(Stock.create(product, cartProductDto.quantity(), StockOperationTypeEnum.SAIDA));
        productRepository.decreaseProductQuantity(cartProductDto.quantity(), cartProductDto.productId());

        return cartProductRepository.save(cartProduct);
    }
}
