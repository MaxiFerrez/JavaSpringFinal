package com.ucc.crudservice.service;

import com.ucc.crudservice.model.Product;
import com.ucc.crudservice.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProducts() {
        Product product = new Product(1L , "SKU001", "Product1", "Description1", 100.0, true);
        List<Product> products = Collections.singletonList(product);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getProducts();
        assertEquals(1, result.size());
        assertEquals("SKU001", result.get(0).getSku());
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;

        ResponseEntity<Object> response = productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully", response.getBody());
    }

    @Test
    public void testAddProduct() {
        Product product = new Product(1L, "SKU001", "Product1", "Description1", 100.0, true);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        ResponseEntity<Object> response = productService.addProduct(product);

        verify(productRepository, times(1)).save(product);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product added successfully", response.getBody());
    }

    @Test
    public void testUpdateProduct() {
        Long productId = 1L;
        Product existingProduct = new Product(productId, "SKU001", "Product1", "Description1", 100.0, true);
        Product updateProduct = new Product(productId, "SKU002", "UpdatedProduct", "UpdatedDescription", 150.0, false);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        ResponseEntity<Object> response = productService.updateProduct(productId, updateProduct);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(existingProduct);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product updated successfully", response.getBody());
        assertEquals("SKU002", existingProduct.getSku());
        assertEquals("UpdatedProduct", existingProduct.getName());
        assertEquals("UpdatedDescription", existingProduct.getDescription());
        assertEquals(150.0, existingProduct.getPrice());
        assertEquals(false, existingProduct.getStatus());
    }
}
