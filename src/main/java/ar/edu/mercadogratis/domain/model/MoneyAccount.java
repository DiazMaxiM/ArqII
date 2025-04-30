package ar.edu.mercadogratis.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class MoneyAccount  {

    private String id;
    private User user;

    private BigDecimal balance;

    public void addToBalance(BigDecimal amount){
        this.balance = balance.add(amount);
    }

    public void subtractFromBalance(BigDecimal amount){
        this.balance = balance.subtract(amount);
    }
}
