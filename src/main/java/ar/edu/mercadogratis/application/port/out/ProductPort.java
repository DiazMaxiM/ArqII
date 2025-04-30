package ar.edu.mercadogratis.application.port.out;
import ar.edu.mercadogratis.domain.model.Product;
import java.util.Optional;
import java.util.List;

public interface ProductPort {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}
