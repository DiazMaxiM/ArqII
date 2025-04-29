package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository;

import ar.edu.mercadogratis.domain.model.User;
import ar.edu.mercadogratis.domain.model.MoneyAccount;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MoneyAccountRepository extends MongoRepository<MoneyAccount, Long> {

    @Query("select u from MoneyAccount u where u.user = ?1")
    Optional<MoneyAccount> getByUser(User user);
}
