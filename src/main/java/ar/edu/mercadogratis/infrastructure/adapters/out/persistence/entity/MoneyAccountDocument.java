package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@Document(collection = "money_accounts")
public class MoneyAccountDocument {

    @Id   private Long id;


    /** Referencia a otro documento en MongoDB */
    @DBRef
    @NotNull
    private UserDocument user;

    @NotNull
    private BigDecimal balance;

    public void addToBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void subtractFromBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
}