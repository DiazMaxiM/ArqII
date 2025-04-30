package ar.edu.mercadogratis.application.port.out;
import ar.edu.mercadogratis.domain.model.User;
import java.util.Optional;

public interface UserPersistencePort {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}
