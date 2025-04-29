package ar.edu.mercadogratis.infrastructure.adapters.in.web.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private Integer stock;
    private Long sellerId;
}