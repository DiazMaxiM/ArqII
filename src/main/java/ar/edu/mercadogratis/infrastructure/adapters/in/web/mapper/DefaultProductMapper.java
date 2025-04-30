package ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper;

import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.ProductDocument;
import org.springframework.stereotype.Component;

@Component
public class DefaultProductMapper implements ProductMapper {

    @Override
    public ProductDocument toDocument(Product domain) {
        if (domain == null) {
            return null;
        }
        ProductDocument doc = new ProductDocument();
        doc.setId(domain.getId());
        doc.setName(domain.getName());
        doc.setDescription(domain.getDescription());
        doc.setCategory(domain.getCategory());
        doc.setPrice(domain.getPrice());
        doc.setStock(domain.getStock());
        doc.setSeller(domain.getSeller());
        doc.setStatus(domain.getStatus());
        return doc;
    }

    @Override
    public Product toDomain(ProductDocument document) {
        if (document == null) {
            return null;
        }
        Product domain = new Product();
        domain.setId(document.getId());
        domain.setName(document.getName());
        domain.setDescription(document.getDescription());
        domain.setCategory(document.getCategory());
        domain.setPrice(document.getPrice());
        domain.setStock(document.getStock());
        domain.setSeller(document.getSeller());
        domain.setStatus(document.getStatus());
        return domain;
    }
}
