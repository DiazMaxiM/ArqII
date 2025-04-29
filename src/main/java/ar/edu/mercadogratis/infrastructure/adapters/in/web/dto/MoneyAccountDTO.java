package ar.edu.mercadogratis.infrastructure.adapters.in.web.dto;

import lombok.Data;

@Data
public class MoneyAccountDTO {
    private Long id;
    private Long userId;
    private Double balance;

}
