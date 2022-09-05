package com.example.shopjava.services;

import com.example.shopjava.entities.product.Product;
import com.example.shopjava.repos.ProductRepo;
import com.example.shopjava.services.impl.FilterProductsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private FilterProductsImpl filterProducts;

    @Mock
    ProductRepo productRepository;

    @Test
    public void addNewProduct(){
        Product product = new Product();
        product.setId(1l);
        product.setName("name");
        product.setBrand("brand");

        when(productRepository.save(product)).thenReturn(product);

        filterProducts.saveProduct(product);

        verify(productRepository).save(product);
    }

    @Test
    public void findProductByIdTest(){
        Product product = new Product();
        product.setId(1l);
        product.setBrand("brand");

        when(productRepository.findProductById(1l)).thenReturn(product);

        Product product1 = filterProducts.getProductById(1l);

        verify(productRepository).findProductById(1l);

        Assertions.assertAll(() -> {
            assertEquals("brand", product1.getBrand());
        });
    }

    @Test
    public void searchProductsTest(){
        Product product1 = new Product();
        product1.setName("name1");

        List<Product> products = new ArrayList<>();
        products.add(product1);

        when(productRepository.searchProducts("name1")).thenReturn(products);

        List<? extends Product> productList = filterProducts.searchProducts("name1");

        verify(productRepository).searchProducts("name1");

        Assertions.assertAll(() -> {
            assertEquals("name1", productList.get(0).getName());
            assertEquals(1, productList.size());
        });
    }

    @Test
    public void sortTest(){
        Product product1 = new Product();
        product1.setName("name1");
        product1.setPrice(10);

        Product product2 = new Product();
        product2.setName("name2");
        product2.setPrice(20);

        List<Product> products1 = new ArrayList<>();
        products1.add(product2);
        products1.add(product1);

        List<? extends Product> products2 = filterProducts.sort(products1, "By name");

        Assertions.assertAll(() -> {
            assertEquals("name1", products2.get(0).getName());
            assertEquals(2, products2.size());
        });

        List<? extends Product> products3 = filterProducts.sort(products1, "From expensive to cheap");

        Assertions.assertAll(() -> {
            assertEquals(20, products3.get(0).getPrice());
            assertEquals(2, products3.size());
        });

        List<? extends Product> products4 = filterProducts.sort(products3, "From cheap to expensive");

        Assertions.assertAll(() -> {
            assertEquals(10, products4.get(0).getPrice());
            assertEquals(2, products4.size());
        });
    }

    @Test
    public void deleteProductByIdTest(){
        filterProducts.deleteById(1l);
        verify(productRepository).deleteById(1l);
    }
}
