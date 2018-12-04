package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Product;
import com.tsystems.tshop.domain.ProductTop;
import com.tsystems.tshop.repositories.CategoryRepository;
import com.tsystems.tshop.repositories.ProductRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);
    private static final String PRICE = "price";
    private static final String COLOR = "color";
    private static final String WEIGHT = "weight";
    private static final String INSTOCK = "inStock";
    private static final String NAME = "name";
    private static final String CATEGORY = "category";
    private static final String ORDER_ASC = "asc";
    private static final String ORDER_DESC = "desc";
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    @Value("${file.path}")
    private String filePath;

    @Autowired
    public ProductService(ProductRepository repository,
                          CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
        this.repository = repository;
    }

    public List<Product> getSortedProducts(String option, String order) {

        if (option.equals(PRICE) && (order.contains("alt"))) {
            return this.repository.findAll(new Sort(Sort.Direction.ASC, PRICE));
        }
        if (option.equals(PRICE) && !(order.contains("alt"))) {
            return this.repository.findAll(new Sort(Sort.Direction.DESC, PRICE));
        } else return this.repository.findAll();
    }

    /**
     * @param products from a shopping cart
     * @return sum of products from a shopping cart
     */
    public BigDecimal getTotal(List<Product> products) {

        BigDecimal total = BigDecimal.valueOf(0);
        List<BigDecimal> values = new ArrayList<>();

        if (!products.isEmpty()) {

            products.forEach(product -> values.add(product.getPrice()));
            total = values.stream()
                    .reduce(BigDecimal::add)
                    .get();
            LOGGER.info("The total for products in the shopping cart is: {}", total);
        }

        return total;
    }


    /**
     * @param product is saved to the database, before that its category is
     *                also saved (if it's a new category)
     */
    public void save(Product product) {

        categoryRepository.save(product.getCategory());
        repository.save(product);
    }

    public List<Product> findProducts() {

        return repository.findAll();

    }

    public Product findProductById(Long productId) {

        Optional<Product> product = this.repository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        } else {
            LOGGER.info("There is no product with id: " + productId.toString());
            throw new RuntimeException();
        }
    }

    public List<Product> findProductByName(String name) {

        return repository.findProductsByNameContains(name);

    }

    @Transactional
    public List<ProductTop> getTopProducts(String order) {

        List<ProductTop> products;
        if (!order.contains("alt")) {
            products = repository.getTopProductsAsc();
            LOGGER.info("products in asc order: ");
            return products;
        } else
            products = repository.getTopProductsDesc();
        LOGGER.info("products in desc order: ");
        return products;
    }

    public List<Product> sortProducts(String columnName, String sortingOrder) {

        if (Objects.isNull(columnName)) {
            return this.repository.findAll();
        } else if (sortingOrder.equals(ORDER_ASC)) {
            if (columnName.equals(INSTOCK)) {
                return repository.findAll(new Sort(Sort.Direction.ASC, INSTOCK));
            } else {
                columnName = columnName.toLowerCase();
                return repository.findAll(new Sort(Sort.Direction.ASC, columnName));
            }
        } else if (sortingOrder.equals(ORDER_DESC)) {
            if (columnName.equals(INSTOCK)) {
                return repository.findAll(new Sort(Sort.Direction.DESC, INSTOCK));
            } else {
                columnName = columnName.toLowerCase();
                return repository.findAll(new Sort(Sort.Direction.DESC, columnName));
            }
        } else return this.repository.findAll();
    }

    public void parseFile(MultipartFile file) {

        file.getContentType();
        LOGGER.info("file content type: " + file.getContentType());
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
            if (Objects.nonNull(file.getOriginalFilename())) {
                File f = new File(filePath, file.getOriginalFilename());
                file.transferTo(f);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Product product = (Product) jaxbUnmarshaller.unmarshal(f);
                repository.save(product);
                LOGGER.info("product from file is: " + product);
            }
        } catch (JAXBException | IOException | NullPointerException e) {
            LOGGER.log(Level.ERROR, "an exception was thrown", e);
            throw new RuntimeException();
        }
    }


}
