package com.example.shopjava.services;

import com.example.shopjava.entities.product.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface FilterProducts {
    List<? extends Product> searchProducts(String keyword);

    List<Product> searchUncertainProducts(String keyword);

    Product getProductById(Long id);

    Map<String, List<String>> getDescTable(Product product);

    List<? extends Product> sort(List<? extends Product> products, String sortType);

    Page<Product> getAllProducts(int pageNum);

    List<Product> getProductsWithDiscount();

    List<Product> getBestsellers();

    Category getCategory(String category);

    void saveProduct(Product product);

    void deleteById(Long id);

    List<Product> bestsellers();

    Map<String, String> descData(Product product);

}
