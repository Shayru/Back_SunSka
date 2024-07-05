package com.akthon.SunSka.service;

import com.akthon.SunSka.DTO.ProductCreateDTO;
import com.akthon.SunSka.DTO.ProductResponseDTO;
import com.akthon.SunSka.DTO.ProductUpdateDTO;
import com.akthon.SunSka.DTO.StockResponseDTO;
import com.akthon.SunSka.model.Product;
import com.akthon.SunSka.model.ProductCategory;
import com.akthon.SunSka.repository.ProductCategoryRepository;
import com.akthon.SunSka.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(ProductCreateDTO productData) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productData.categoryId);
        if(productCategory.isEmpty()) {
            return null;
        }

        Product product = new Product(productData.name, productData.capacity, productData.unit, productCategory.get());
        return productRepository.save(product);
    }

    public Product changeActive(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            return null;
        }

        product.get().changeActif();
        return productRepository.save(product.get());
    }

    public Optional<Product> updateProduct(Long id, ProductUpdateDTO productData) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(productData.name);
            existingProduct.setCapacity(productData.capacity);
            existingProduct.setUnit(productData.unit);
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
