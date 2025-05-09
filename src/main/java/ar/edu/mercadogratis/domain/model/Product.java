package ar.edu.mercadogratis.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import java.math.BigDecimal;
import java.util.Objects;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Product {


    private Long id;
    private String name;

    private String description;

    private ProductCategory category;

    private BigDecimal price;

    private int stock;

    private String seller;

    private ProductStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
      //  if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
