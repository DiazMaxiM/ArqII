package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity;

import ar.edu.mercadogratis.domain.model.ProductCategory;
import ar.edu.mercadogratis.domain.model.ProductStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Document(collection = "products")
public class ProductDocument {
    @Id private Long id;
    private String name;
    private String description;
    private ProductCategory category;
    private BigDecimal price;
    private int stock;
    private String seller;
    private ProductStatus status;
}