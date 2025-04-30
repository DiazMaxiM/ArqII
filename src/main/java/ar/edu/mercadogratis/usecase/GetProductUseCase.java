package ar.edu.mercadogratis.usecase;

import ar.edu.mercadogratis.domain.model.Product;

import java.util.Optional;

public interface GetProductUseCase {
    Optional<Product> getProduct(Long productId);
}
