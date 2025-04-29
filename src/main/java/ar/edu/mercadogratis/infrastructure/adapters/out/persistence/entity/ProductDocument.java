package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity;

import ar.edu.mercadogratis.domain.model.ProductCategory;
import ar.edu.mercadogratis.domain.model.ProductStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "products")
public class ProductDocument {

    @Id
    private String id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private ProductCategory category;

    @Min(1)
    private BigDecimal price;

    @Min(0)
    private int stock;

    @NotBlank
    private String seller;

    @NotNull
    private ProductStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDocument)) return false;
        ProductDocument that = (ProductDocument) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
