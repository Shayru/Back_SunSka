package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.ProductUpdateDTO;
import com.akthon.SunSka.model.Product;
import com.akthon.SunSka.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, ProductUpdateDTO productData) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(productData.name);
            existingProduct.setCapacity(productData.capacity);
            existingProduct.setUnit(productData.unit);
            existingProduct.setUnitCase(productData.unitCase);
            return productRepository.save(existingProduct);
        });
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElse(false);
    }

}
