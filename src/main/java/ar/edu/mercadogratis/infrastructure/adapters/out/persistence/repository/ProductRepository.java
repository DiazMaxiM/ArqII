package ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository;

import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductDocument, Long> {
    @Query("select p from Product p where p.seller = ?1 and p.status <> 'DELETED'")
    List<ProductDocument> findBySeller(String seller);}
