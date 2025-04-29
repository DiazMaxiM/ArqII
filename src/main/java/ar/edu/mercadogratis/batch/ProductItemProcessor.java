package ar.edu.mercadogratis.infrastructure.adapters.in.web;

import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.domain.model.ProductStatus;
import ar.edu.mercadogratis.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;

@RequiredArgsConstructor
public class ProductItemProcessor implements ItemProcessor<Product, Product> {

    private final User seller;

    @Override
    public Product process(Product product) {
        return product.toBuilder()
                .status(ProductStatus.ACTIVE)
                .seller(seller.getEmail())
                .build();
    }
}
