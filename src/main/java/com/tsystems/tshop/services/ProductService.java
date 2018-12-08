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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileSystemNotFoundException;
import java.util.*;

@Service
@Transactional
public class ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductService.class);
    private static final String INSTOCK = "inStock";
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

    /**
     * @param productId used to find a product
     * @return a product with a specified id
     * @throws NoSuchElementException if there is no product with the specified id
     */
    public Product findProductById(Long productId) {

        Optional<Product> product = this.repository.findById(productId);
        if (product.isPresent()) {
            return product.get();
        } else {
            LOGGER.info("There is no product with id: " + productId.toString());
            throw new NoSuchElementException();
        }
    }

    /**
     * @param name used to find products that have this name
     * @return a list of products that contain the specified name
     */
    public List<Product> findProductByName(String name) {

        return repository.findProductsByNameContains(name);

    }

    /**
     * @param order defines the order in which the products should be listed
     * @return a list of top products in a particular order
     */
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

    /**
     * @param columnName   the column which will be used for sorting
     * @param sortingOrder an order in which the products are listed
     * @return a list of products sorted by a particular column in ascending or descending order
     * or, if the parameters are not specified, a list of products as they appear in the database
     */
    public List<Product> sortProducts(String columnName, String sortingOrder) {

        if (Objects.isNull(columnName)) {
            return repository.findAll();
        } else if (ORDER_ASC.equals(sortingOrder)) {
            if (columnName.equals(INSTOCK)) {
                return repository.findAll(new Sort(Sort.Direction.ASC, INSTOCK));
            } else {
                columnName = columnName.toLowerCase();
                return repository.findAll(new Sort(Sort.Direction.ASC, columnName));
            }
        } else if (ORDER_DESC.equals(sortingOrder)) {
            if (columnName.equals(INSTOCK)) {
                return repository.findAll(new Sort(Sort.Direction.DESC, INSTOCK));
            } else {
                columnName = columnName.toLowerCase();
                return repository.findAll(new Sort(Sort.Direction.DESC, columnName));
            }
        } else return repository.findAll();
    }

    /**
     * Creates a new Product in the database using information uploaded by the salesperson
     *
     * @param file a file uploaded by the salesperson
     */
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
            throw new FileSystemNotFoundException();
        }
    }


}
