package ar.edu.mercadogratis.infrastructure.adapters.in.web.controllers;

import ar.edu.mercadogratis.application.exceptions.NotFoundException;
import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.domain.model.ProductCategory;
import ar.edu.mercadogratis.domain.model.SearchProductRequest;

import ar.edu.mercadogratis.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final GetProductUseCase getProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final SaveProductUseCase saveProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ListProductUseCase listProductUseCase;
    private final SearchProductUseCase searchProductUseCase;

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return getProductUseCase.getProduct(productId)
                .orElseThrow(() -> new NotFoundException("product_not_found", "Product not found: " + productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        deleteProductUseCase.deleteProduct(productId);
        return ResponseEntity.ok("ok");
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        Product saved = saveProductUseCase.saveProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(saved);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@Valid @RequestBody Product product) {
        updateProductUseCase.updateProduct(product);
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public Iterable<Product> listProducts(@RequestParam String seller) {
        return listProductUseCase.listProducts(seller);
    }

    @GetMapping("/search")
    public Iterable<Product> searchProducts(@RequestParam Optional<String> name,
                                            @RequestParam Optional<ProductCategory> category,
                                            @RequestParam(name = "min_price") Optional<BigDecimal> minPrice,
                                            @RequestParam(name = "max_price") Optional<BigDecimal> maxPrice) {
        SearchProductRequest search = SearchProductRequest.builder()
                .category(category)
                .name(name)
                .maxPrice(maxPrice)
                .minPrice(minPrice)
                .minStock(1)
                .build();
        return searchProductUseCase.searchProduct(search);
    }
}
