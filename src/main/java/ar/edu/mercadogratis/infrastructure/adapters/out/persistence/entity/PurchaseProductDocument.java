package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity;

import ar.edu.mercadogratis.domain.model.PurchaseStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "purchases")
public class PurchaseProductDocument {

    @Id
    private String id;

    /** Referencia al documento Product */
    @DBRef
    @NotNull
    private ProductDocument product;

    /** Referencia al documento User que compra */
    @DBRef
    @NotNull
    private UserDocument buyer;

    @NotNull
    private LocalDateTime creationDate;

    @Min(1)
    private int quantity;

    @NotNull
    private PurchaseStatus status;

    public BigDecimal getPrice() {
        return product.getPrice();
    }
}