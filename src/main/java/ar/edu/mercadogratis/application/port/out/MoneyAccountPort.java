package ar.edu.mercadogratis.application.port.out;
import ar.edu.mercadogratis.domain.model.MoneyAccount;
import ar.edu.mercadogratis.domain.model.User;
import java.util.Optional;

public interface MoneyAccountPort {
    MoneyAccount save(MoneyAccount account);
    Optional<MoneyAccount> findById(Long id);
    Optional<MoneyAccount> getByUser(User user);
    void deleteById(Long id);
}