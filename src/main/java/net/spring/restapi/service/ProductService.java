package net.spring.restapi.service;

import net.spring.restapi.model.Product;

import java.util.List;

public interface ProductService {
    Product insert(Product productList);

    List<Product> getAllProducts();

    boolean isProductExists(Long id);

    Product saveProduct(Product productList);

    Double calculateTotalPrice();

    Double calculateTotalTax();

    Product getProductById(Long id);

    void deleteProductById(Long id);
}