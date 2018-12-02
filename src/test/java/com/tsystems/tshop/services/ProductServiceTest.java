package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.repositories.CategoryRepository;
import com.tsystems.tshop.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {

    ProductService productService;
    @Mock
    private ProductRepository repository;
    @Mock
    private CategoryRepository categoryRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(repository, categoryRepository);
        product = new Product();
        product.setPrice(BigDecimal.valueOf(100));
    }

    @Test
    void getSortedProducts() {
    }

    @Test
    void getTotal_whenProductListEmpty_thenReturnZero() {
        List<Product> products = new ArrayList<>();
        BigDecimal total = BigDecimal.valueOf(0);
        assertEquals(total, productService.getTotal(products));
    }

    @Test
    void getTotal_whenProductListNotEmpty_thenReturnTotal() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        BigDecimal total = BigDecimal.valueOf(100);
        assertEquals(total, productService.getTotal(products));
    }

    @Test
    void save() {
    }

    @Test
    void findProducts() {
    }

    @Test
    void findProductById() {
    }

    @Test
    void findProductByName() {
    }

    @Test
    void getTopProducts() {
    }

    @Test
    void sortProducts() {
    }

    @Test
    void sortProducts1() {
    }

    @Test
    void parseFile() {
    }
}