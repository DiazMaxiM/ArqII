package ar.edu.mercadogratis.infrastructure.adapters.out.persistence;
import ar.edu.mercadogratis.application.port.out.PurchaseProductPort;
import ar.edu.mercadogratis.domain.model.PurchaseProduct;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository.PurchaseProductRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class PurchaseProductAdapter implements PurchaseProductPort {
    private final PurchaseProductRepository repository;

    public PurchaseProductAdapter(PurchaseProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseProduct save(PurchaseProduct purchaseProduct) {
        return repository.save(purchaseProduct);
    }

    @Override
    public Optional<PurchaseProduct> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PurchaseProduct> findAll() {
        return (List<PurchaseProduct>) repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}