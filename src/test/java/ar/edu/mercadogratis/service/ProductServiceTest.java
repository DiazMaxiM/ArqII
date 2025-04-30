package ar.edu.mercadogratis.service;

import ar.edu.mercadogratis.application.service.ProductService;
import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper.ProductMapper;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.entity.ProductDocument;
import ar.edu.mercadogratis.infrastructure.adapters.out.persistence.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    private ProductService service;

    @BeforeEach
    void setUp() {
        service = new ProductService(repository, mapper);
    }

    @Test
    void whenGetProduct_thenReturnsDomainObject() {
        Long id = 1L;

        ProductDocument doc = new ProductDocument();
        doc.setId(id);
        doc.setName("X");

        Product domain = new Product();
        domain.setId(id);
        domain.setName("X");

        when(repository.findById(id)).thenReturn(Optional.of(doc));
        when(mapper.toDomain(doc)).thenReturn(domain);

        Optional<Product> result = service.getProduct(id);

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());

        verify(repository).findById(id);
        verify(mapper).toDomain(doc);
    }

    @Test
    void whenSaveProduct_thenRepositoryAndMapperAreUsed() {
        Product inputDomain = new Product();
        inputDomain.setName("Foo");

        ProductDocument toSaveDoc = new ProductDocument();
        toSaveDoc.setName("Foo");

        ProductDocument savedDoc = new ProductDocument();
        savedDoc.setId(2L);
        savedDoc.setName("Foo");

        Product outputDomain = new Product();
        outputDomain.setId(2L);
        outputDomain.setName("Foo");

        when(mapper.toDocument(inputDomain)).thenReturn(toSaveDoc);
        when(repository.save(toSaveDoc)).thenReturn(savedDoc);
        when(mapper.toDomain(savedDoc)).thenReturn(outputDomain);

        Product result = service.saveProduct(inputDomain);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Foo", result.getName());

        verify(mapper).toDocument(inputDomain);
        verify(repository).save(toSaveDoc);
        verify(mapper).toDomain(savedDoc);
    }

    @Test
    void whenListProducts_thenReturnsMappedList() {
        ProductDocument d1 = new ProductDocument(); d1.setId(1L); d1.setName("A");
        ProductDocument d2 = new ProductDocument(); d2.setId(2L); d2.setName("B");
        List<ProductDocument> docs = List.of(d1, d2);

        Product p1 = new Product(); p1.setId(1L); p1.setName("A");
        Product p2 = new Product(); p2.setId(2L); p2.setName("B");

        when(repository.findBySeller("alice")).thenReturn(docs);
        when(mapper.toDomain(d1)).thenReturn(p1);
        when(mapper.toDomain(d2)).thenReturn(p2);

        Iterable<Product> resultIterable = service.listProducts("alice");
        List<Product> resultList = new ArrayList<>();
        resultIterable.forEach(resultList::add);

        assertEquals(2, resultList.size());
        assertEquals(List.of(p1, p2), resultList);

        verify(repository).findBySeller("alice");
        verify(mapper).toDomain(d1);
        verify(mapper).toDomain(d2);
    }

    @Test
    void whenDeleteProduct_thenRepositoryDeleteCalled() {
        Long id = 3L;
        service.deleteProduct(id);
        verify(repository, times(1)).deleteById(eq(id));
    }
}
