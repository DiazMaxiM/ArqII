package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("users")
@Data
@Getter
@Setter
public class UserDocument {
    @Id   private Long id;
    private String name;
    private String lastName;
    //@Column(name = "email", nullable = false, unique = true)
    private String email;
    private String password;
    private String cuit;


}
