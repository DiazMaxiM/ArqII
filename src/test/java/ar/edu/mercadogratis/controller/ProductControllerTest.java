package ar.edu.mercadogratis.controller;

import ar.edu.mercadogratis.domain.model.Product;
import ar.edu.mercadogratis.domain.model.ProductCategory;
import ar.edu.mercadogratis.domain.model.ProductStatus;
import ar.edu.mercadogratis.infrastructure.adapters.in.web.controllers.ProductController;
import ar.edu.mercadogratis.usecase.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper mapper;

    @MockBean private GetProductUseCase getProductUseCase;
    @MockBean private DeleteProductUseCase deleteProductUseCase;
    @MockBean private SaveProductUseCase saveProductUseCase;
    @MockBean private UpdateProductUseCase updateProductUseCase;
    @MockBean private ListProductUseCase listProductUseCase;
    @MockBean private SearchProductUseCase searchProductUseCase;

    private Product sampleProduct() {
        Product p = new Product();
        p.setId(1L);
        p.setName("Foo");
        p.setPrice(new BigDecimal("9.99"));
        p.setStock(5);
        p.setSeller("alice");
        p.setCategory(ProductCategory.TECHNOLOGY);
        p.setStatus(ProductStatus.ACTIVE);
        return p;
    }

    @Nested @DisplayName("GET /products")
    class ListAndSearch {
        @Test
        void shouldListAllForSeller() throws Exception {
            when(listProductUseCase.listProducts("alice"))
                    .thenReturn(List.of(sampleProduct()));

            mvc.perform(get("/products").param("seller","alice"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].name").value("Foo"));
        }
    }

    

    @Nested @DisplayName("POST /products")
    class Create {
        @Test
        void shouldCreateAndReturn() throws Exception {
            Product toSave = new Product();
            toSave.setName("Foo");
            toSave.setPrice(new BigDecimal("9.99"));
            toSave.setStock(5);
            toSave.setSeller("alice");
            toSave.setCategory(ProductCategory.TECHNOLOGY);
            toSave.setStatus(ProductStatus.ACTIVE);

            when(saveProductUseCase.saveProduct(any(Product.class)))
                    .thenReturn(sampleProduct());

            mvc.perform(post("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(toSave)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.price").value(9.99));
        }
    }

    @Nested @DisplayName("PUT /products")
    class Update {
        @Test
        void shouldUpdate() throws Exception {
            Product updateReq = sampleProduct();
            updateReq.setName("Bar");

            doNothing().when(updateProductUseCase).updateProduct(any(Product.class));

            mvc.perform(put("/products")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(updateReq)))
                    .andExpect(status().isOk())
                    .andExpect(content().string("ok"));
        }
    }

    @Nested @DisplayName("DELETE /products/{id}")
    class Delete {
        @Test
        void shouldDelete() throws Exception {
            doNothing().when(deleteProductUseCase).deleteProduct(1L);

            mvc.perform(delete("/products/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("ok"));
        }
    }
}
