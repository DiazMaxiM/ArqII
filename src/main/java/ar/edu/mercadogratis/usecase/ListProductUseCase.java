package ar.edu.mercadogratis.usecase;

import ar.edu.mercadogratis.domain.model.Product;

public interface ListProductUseCase {
    Iterable<Product> listProducts(String sellerMail);
}
