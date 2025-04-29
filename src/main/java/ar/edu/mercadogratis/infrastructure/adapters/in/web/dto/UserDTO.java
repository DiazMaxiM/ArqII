package ar.edu.mercadogratis.infrastructure.adapters.in.web.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String cuit;
}