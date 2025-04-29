package ar.edu.mercadogratis.infrastructure.adapters.in.web;

import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.domain.model.ProductCategory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class ProductFieldMapper implements FieldSetMapper<Product> {
    @Override
    public Product mapFieldSet(FieldSet fieldSet) {
        return Product.builder()
                .name(fieldSet.readString(0))
                .description(fieldSet.readString(1))
                .category(ProductCategory.valueOf(fieldSet.readString(2)))
                .price(fieldSet.readBigDecimal(3))
                .stock(fieldSet.readInt(4))
                .build();
    }
}
