package ar.edu.mercadogratis.infrastructure.adapters.in.web.dto;

import lombok.Data;

@Data
public class AddFundsRequestDTO {
    private Long userId;
    private Double amount;
}