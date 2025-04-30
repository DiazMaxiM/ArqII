package ar.edu.mercadogratis.infrastructure.adapters.out.persistence;

import ar.edu.mercadogratis.application.port.out.MoneyAccountPort;
import ar.edu.mercadogratis.domain.model.MoneyAccount;
import ar.edu.mercadogratis.domain.model.User;
import ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper.MoneyAccountMapper;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.MoneyAccountDocument;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository.MoneyAccountRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class MoneyAccountAdapter implements MoneyAccountPort {
    private final MoneyAccountRepository repository;
    private final MoneyAccountMapper mapper;

    public MoneyAccountAdapter(MoneyAccountRepository repository,
                               MoneyAccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MoneyAccount save(MoneyAccount account) {
        MoneyAccountDocument doc = mapper.toDocument(account);
        MoneyAccountDocument saved = repository.save(doc);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<MoneyAccount> findById(Long id) {
        return repository
                .findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<MoneyAccount> getByUser(User user) {
        return Optional.ofNullable(mapper.toDomain(repository.getByUser(user)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
