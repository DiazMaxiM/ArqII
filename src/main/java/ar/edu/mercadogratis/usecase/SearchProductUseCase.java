package ar.edu.mercadogratis.usecase;

import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.domain.model.SearchProductRequest;

import java.util.List;

public interface SearchProductUseCase {
    public List<Product> searchProduct(SearchProductRequest searchProductRequest);
}
