package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository;

import ar.edu.mercadogratis.domain.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, Long> {
    @Query("select p from Product p where p.seller = ?1 and p.status <> 'DELETED'")
    Iterable<Product> findBySeller(String seller);
}
