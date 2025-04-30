package ar.edu.mercadogratis.usecase;

import ar.edu.mercadogratis.domain.model.Product;

public interface SaveProductUseCase {
    Product saveProduct(Product product);
}
