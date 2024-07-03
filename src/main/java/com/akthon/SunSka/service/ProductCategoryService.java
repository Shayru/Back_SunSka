package com.akthon.SunSka.service;

import com.akthon.SunSka.model.ProductCategory;
import com.akthon.SunSka.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public Optional<ProductCategory> updateProductCategory(Long id, ProductCategory productCategory) {
        return productCategoryRepository.findById(id).map(existingProductCategory -> {
            existingProductCategory.setName(productCategory.getName());
            return productCategoryRepository.save(existingProductCategory);
        });
    }

    public boolean deleteProductCategory(Long id) {
        return productCategoryRepository.findById(id).map(productCategory -> {
            productCategoryRepository.delete(productCategory);
            return true;
        }).orElse(false);
    }
}
