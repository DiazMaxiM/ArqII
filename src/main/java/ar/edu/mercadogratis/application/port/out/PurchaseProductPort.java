package ar.edu.mercadogratis.application.port.out;
import ar.edu.mercadogratis.domain.model.PurchaseProduct;
import java.util.Optional;
import java.util.List;
public interface PurchaseProductPort {
    PurchaseProduct save(PurchaseProduct purchaseProduct);
    Optional<PurchaseProduct> findById(Long id);
    List<PurchaseProduct> findAll();
    void deleteById(Long id);
}
