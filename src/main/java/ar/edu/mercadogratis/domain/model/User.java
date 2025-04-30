package ar.edu.mercadogratis.domain.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String cuit;

}