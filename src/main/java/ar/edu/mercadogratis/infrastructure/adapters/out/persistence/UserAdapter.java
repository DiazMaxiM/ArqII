package ar.edu.mercadogratis.infrastructure.adapters.out.persistence;
import ar.edu.mercadogratis.domain.model.User;
import ar.edu.mercadogratis.application.port.out.UserPersistencePort;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.UserDocument;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAdapter implements UserPersistencePort {

    private final UserRepository repo;

    public UserAdapter(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User save(User u) {
        UserDocument doc = toDocument(u);
        doc = repo.save(doc);
        return toDomain(doc);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repo.findByEmailContainingIgnoreCase(email)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findById(String id) {
        return repo.findById(id)
                .map(this::toDomain);
    }

    private UserDocument toDocument(User u) {
        UserDocument d = new UserDocument();
        d.setId(u.getId());
        d.setName(u.getName());
        d.setLastName(u.getLastName());
        d.setEmail(u.getEmail());
        d.setPassword(u.getPassword());
        d.setCuit(u.getCuit());
        return d;
    }

    private User toDomain(UserDocument d) {
        User u = new User();
        u.setId(d.getId());
        u.setName(d.getName());
        u.setLastName(d.getLastName());
        u.setEmail(d.getEmail());
        u.setPassword(d.getPassword());
        u.setCuit(d.getCuit());
        return u;
    }
}