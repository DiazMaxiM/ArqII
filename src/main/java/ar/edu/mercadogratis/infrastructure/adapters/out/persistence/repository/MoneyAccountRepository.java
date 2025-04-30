package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository;

import ar.edu.mercadogratis.domain.model.User;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.MoneyAccountDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;

public interface MoneyAccountRepository extends MongoRepository<MoneyAccountDocument, Long> {

    @Query("select u from MoneyAccount u where u.user = ?1")
    MoneyAccountDocument getByUser(User user);
}
