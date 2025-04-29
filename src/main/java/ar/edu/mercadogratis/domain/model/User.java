package ar.edu.mercadogratis.domain.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String cuit;

}