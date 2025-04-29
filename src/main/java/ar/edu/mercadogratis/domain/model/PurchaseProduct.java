package ar.edu.mercadogratis.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseProduct extends BaseEntity {

    private Product product;


    private User buyer;

    private LocalDateTime creationDate;

    private int quantity;

    private PurchaseStatus status;

    public BigDecimal getPrice() {
        return product.getPrice();
    }
}
