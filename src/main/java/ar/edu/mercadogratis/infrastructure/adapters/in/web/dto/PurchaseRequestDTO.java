package ar.edu.mercadogratis.infrastructure.adapters.in.web.dto;

import lombok.Data;

@Data
public class PurchaseRequestDTO {
    private Long productId;
    private Integer quantity;
    private String buyerEmail;
}