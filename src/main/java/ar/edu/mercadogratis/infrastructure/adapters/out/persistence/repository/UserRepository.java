package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository;

import ar.edu.mercadogratis.domain.model.User;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository
        extends MongoRepository<UserDocument, Long> {

    /** Busca por email exacto (case-insensitive) */
    Optional<UserDocument> findByEmailIgnoreCase(String email);

    /** O busca por email contenga texto, ignorando mayúsculas */
    Optional<UserDocument> findByEmailContainingIgnoreCase(String fragment);
    @Query("{ 'email' : { $regex: ?0, $options: 'i' } }")
    List<UserDocument> searchByEmailRegex(String regex);
}
