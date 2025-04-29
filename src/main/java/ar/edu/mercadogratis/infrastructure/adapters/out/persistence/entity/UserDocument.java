package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@ToString
@Document(collection = "users")
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDocument {
    @Id
    private String id;
    private String name;
    private String lastName;
    //@Column(name = "email", nullable = false, unique = true)
    private String email;
    private String password;
    private String cuit;


}
