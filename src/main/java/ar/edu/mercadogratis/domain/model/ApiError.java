package ar.edu.mercadogratis.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String cause;
    private ApiError internalCause;
    private String message;
    private Integer status;
}
