package net.spring.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.spring.restapi.model.Product;
import net.spring.restapi.service.ProductService;


import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //want to work on
    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    // handler method to handle list products and return mode and view
    @GetMapping("/products")
    public List<Product> listProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products/new")
    public Product createProductList(@RequestBody Product productList) {
        productService.insert(productList);
        return productService.saveProduct(productList);
    }

    @GetMapping("/TotalPrice")
    public Double CalcTotalPrice() {
        return productService.calculateTotalPrice();
    }

    @GetMapping("/TotalTax")
    public Double CalcTotalTax() {
        return productService.calculateTotalTax();
    }

    // handler method to handle delete product request
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        if (productService.isProductExists(id)) {
            productService.deleteProductById(id);
            return "Deleted the Product successfully!";
        } else {
            return "No such Product to delete from your bill!";
        }
    }
}
