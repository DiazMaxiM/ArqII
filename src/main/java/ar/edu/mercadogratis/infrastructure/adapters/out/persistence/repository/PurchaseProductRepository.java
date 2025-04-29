package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository;

import ar.edu.mercadogratis.domain.model.PurchaseProduct;
import ar.edu.mercadogratis.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PurchaseProductRepository extends MongoRepository<PurchaseProduct, Long> {
    @Query("select p from PurchaseProduct p where p.buyer = ?1")
    Iterable<PurchaseProduct> findByBuyer(User buyer);
}
