package ar.edu.mercadogratis.application.service;

import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository.ProductRepository;
import ar.edu.mercadogratis.domain.model.SearchProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Iterable<Product> saveAllProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Transactional
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public Iterable<Product> listProducts(String seller) {
        return productRepository.findBySeller(seller);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Transactional
    public List<Product> searchProduct(SearchProductRequest searchProductRequest) {
        return null;//productRepository.findAll(new ProductSpecification(searchProductRequest));
    }

    @Transactional
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
