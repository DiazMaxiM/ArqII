package ar.edu.mercadogratis.infrastructure.adapters.in.web.dto;

import lombok.Data;

@Data
public class SearchProductRequestDTO {
    private String name;
    private String category;
    private Double minPrice;
    private Double maxPrice;
    private Integer minStock;
}
