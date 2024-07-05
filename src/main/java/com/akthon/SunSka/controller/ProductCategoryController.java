package com.akthon.SunSka.controller;

import com.akthon.SunSka.DTO.ProductCreateDTO;
import com.akthon.SunSka.model.ProductCategory;
import com.akthon.SunSka.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping
    public List<ProductCategory> getAll() {
        return productCategoryService.getAll();
    }

    @PostMapping
    public ProductCategory createProductCategory(@RequestBody ProductCreateDTO categoryData) {
        return productCategoryService.createProductCategory(categoryData.name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable Long id, @RequestBody ProductCreateDTO categoryData) {
        Optional<ProductCategory> updatedProductCategory = productCategoryService.updateProductCategory(id, categoryData.name);
        return updatedProductCategory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {
        boolean isDeleted = productCategoryService.deleteProductCategory(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
