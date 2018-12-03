package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.ProductCategory;
import com.tsystems.tshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    public List<ProductCategory> getCategories() {

        return categoryRepository.findAll();
    }

    public ProductCategory getCategoryByName(String name) {

        ProductCategory cat = categoryRepository.findProductCategoryByCategoryName(name);
        if (Objects.isNull(cat)) {
            ProductCategory category = new ProductCategory();
            category.setCategoryName(name);
            categoryRepository.save(category);
            return category;
        } else return cat;

    }
}
