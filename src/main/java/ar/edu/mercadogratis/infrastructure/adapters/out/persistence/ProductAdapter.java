package ar.edu.mercadogratis.infrastructure.adapters.out.persistence;
import ar.edu.mercadogratis.application.port.out.ProductPort;
import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper.ProductMapper;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.ProductDocument;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements ProductPort {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public Product save(Product product) {
        // 1) Dom → Doc
        ProductDocument doc   = mapper.toDocument(product);
        // 2) Guarda el documento
        ProductDocument saved = repository.save(doc);
        // 3) Doc → Dom
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}