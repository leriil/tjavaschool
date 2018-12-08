package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.ProductTop;
import com.tsystems.tshop.repositories.CategoryRepository;
import com.tsystems.tshop.repositories.ProductRepository;
import org.apache.logging.log4j.Level;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.FileSystemNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    List<Product> products;
    private ProductService productService;
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
        products = new ArrayList<>();
    }

    @Test
    void getTotal_whenProductListEmpty_thenReturnZero() {

        BigDecimal total = BigDecimal.valueOf(0);
        assertEquals(total, productService.getTotal(products));
    }

    @Test
    void getTotal_whenProductListNotEmpty_thenReturnTotal() {

        products.add(product);
        BigDecimal total = BigDecimal.valueOf(100);
        assertEquals(total, productService.getTotal(products));
    }

    @Test
    void save() {

        productService.save(product);
    }

    @Test
    void findProducts() {

        products.add(product);
        when(repository.findAll()).thenReturn(products);
        assertTrue(!productService.findProducts().isEmpty());
    }

    @Test
    void findProductById_whenProductExists_thenReturnProduct() {

        when(repository.findById(product.getProductId())).thenReturn(Optional.of(product));
        assertEquals(product, productService.findProductById(product.getProductId()));
    }

    @Test
    void findProductById_whenProductDoesNotExist_thenThrowObjectNotFoundException() {

        Optional<Product> product = Optional.empty();
        when(repository.findById(100L)).thenReturn(product);
        assertThrows(NoSuchElementException.class, () -> productService.findProductById(1000L));
    }

    @Test
    void findProductByName_whenProductsExists_thenReturnProductList() {

        String watch = "watch";
        products.add(product);
        when(repository.findProductsByNameContains(anyString())).thenReturn(products);
        List<Product> products = productService.findProductByName(watch);
        assertTrue(!products.isEmpty());
    }

    @Test
    void findProductById_whenProductsDoNotExist_thenReturnOptionalEmpty() {

        String spider = "spider";
        List<Product> products = new ArrayList<>();
        lenient().when(repository.findProductsByNameContains(spider)).thenReturn(products);
        assertTrue(productService.findProductByName(spider).isEmpty());
    }

    @Test
    void getTopProducts() {

        List<ProductTop> topProducts = new ArrayList<>();
        ProductTop productTop = new ProductTop();
        productTop.setProductId(1L);
        topProducts.add(productTop);
        when(repository.getTopProductsDesc()).thenReturn(topProducts);
        when(repository.getTopProductsAsc()).thenReturn(topProducts);
        assertAll(
                () -> assertFalse(productService.getTopProducts("alt").isEmpty()),
                () -> assertFalse(productService.getTopProducts("bee").isEmpty())
        );
    }

    @Nested
    class sortProducts {

        String name;
        String inStock;
        String order_asc;
        String order_desc;


        @BeforeEach
        void init(){
            name = "Name";
            inStock = "inStock";
            order_asc = "asc";
            order_desc = "desc";
            products.add(product);

        }

        @Test
        void sortProducts() {

            when(repository.findAll()).thenReturn(products);
            productService.sortProducts(null,order_asc);
            verify(repository).findAll();
        }

        @Test
        void sortProducts_whenColumnNameNullAndOrderNull_thenReturnAllProducts() {

            when(repository.findAll()).thenReturn(products);
            List<Product>productList = productService.sortProducts(name,null);
            assertFalse(productList.isEmpty());
            verify(repository).findAll();
        }

        @Test
        void sortProducts_whenColumnNameNameOrderAsc_thenReturnSortedProducts() {

            when(repository.findAll(new Sort(Sort.Direction.ASC,name.toLowerCase()))).thenReturn(products);
            productService.sortProducts(name,order_asc);
            verify(repository).findAll(new Sort(Sort.Direction.ASC,name.toLowerCase()));
        }

        @Test
        void sortProducts_whenColumnNameinStockOrderAsc_thenReturnSortedProducts() {

            when(repository.findAll(new Sort(Sort.Direction.ASC,inStock))).thenReturn(products);
            productService.sortProducts(inStock,order_asc);
            verify(repository).findAll(new Sort(Sort.Direction.ASC,inStock));
        }

        @Test
        void sortProducts_whenColumnNameNameOrderDesc_thenReturnSortedProducts() {

            when(repository.findAll(new Sort(Sort.Direction.DESC,name.toLowerCase()))).thenReturn(products);
            productService.sortProducts(name,order_desc);
            verify(repository).findAll(new Sort(Sort.Direction.DESC,name.toLowerCase()));
        }

        @Test
        void sortProducts_whenColumnNameinStockOrderDesc_thenReturnSortedProducts() {

            when(repository.findAll(new Sort(Sort.Direction.DESC,inStock))).thenReturn(products);
            productService.sortProducts(inStock,order_desc);
            verify(repository).findAll(new Sort(Sort.Direction.DESC,inStock));
        }
    }
}