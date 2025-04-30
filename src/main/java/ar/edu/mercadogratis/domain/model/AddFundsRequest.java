package ar.edu.mercadogratis.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddFundsRequest {
    private Long userId;
    private BigDecimal amount;
}
