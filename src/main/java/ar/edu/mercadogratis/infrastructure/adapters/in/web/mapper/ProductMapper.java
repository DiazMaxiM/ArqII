package ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper;

import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.ProductDocument;


public interface ProductMapper {

    default ProductDocument toDocument(Product d) {
        if (d == null) return null;
        ProductDocument doc = new ProductDocument();
        doc.setId(d.getId());
        doc.setName(d.getName());
        doc.setDescription(d.getDescription());
        doc.setCategory(d.getCategory());
        doc.setPrice(d.getPrice());
        doc.setStock(d.getStock());
        doc.setSeller(d.getSeller());
        doc.setStatus(d.getStatus());
        return doc;
    }

    default Product toDomain(ProductDocument doc) {
        if (doc == null) return null;
        Product d = new Product();
        d.setId(doc.getId());
        d.setName(doc.getName());
        d.setDescription(doc.getDescription());
        d.setCategory(doc.getCategory());
        d.setPrice(doc.getPrice());
        d.setStock(doc.getStock());
        d.setSeller(doc.getSeller());
        d.setStatus(doc.getStatus());
        return d;
    }
}