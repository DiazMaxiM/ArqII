package ar.edu.mercadogratis.application.service;

import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper.ProductMapper;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.ProductDocument;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository.ProductRepository;
import ar.edu.mercadogratis.domain.model.SearchProductRequest;
import ar.edu.mercadogratis.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService implements
        GetProductUseCase,
        DeleteProductUseCase,
        SaveProductUseCase,
        UpdateProductUseCase,
        ListProductUseCase,
        SearchProductUseCase {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toDomain);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        ProductDocument doc    = productMapper.toDocument(product);
        ProductDocument saved  = productRepository.save(doc);
        return productMapper.toDomain(saved);
    }


    @Transactional
    public Iterable<Product> saveAllProducts(List<Product> products) {
        List<ProductDocument> docs  = products.stream()
                .map(productMapper::toDocument)
                .collect(Collectors.toList());

        List<ProductDocument> saved = productRepository.saveAll(docs);
        return saved.stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        // Igual que save: mapea y guarda
        ProductDocument doc = productMapper.toDocument(product);
        productRepository.save(doc);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Product> listProducts(String seller) {
        return productRepository.findBySeller(seller)
                .stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProduct(SearchProductRequest req) {
        // Implementa aquí tu lógica de búsqueda y mapeo…
        return null;// List.of();
    }

    @Transactional
    public void deleteAll() {
        productRepository.deleteAll();
    }
}