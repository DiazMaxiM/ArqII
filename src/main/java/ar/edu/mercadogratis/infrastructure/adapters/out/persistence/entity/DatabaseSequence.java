package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "database_sequences")
@Data
public class DatabaseSequence {
    @Id
    private String id;    // p.ej. "users_sequence" o "products_sequence"
    private long seq;
}