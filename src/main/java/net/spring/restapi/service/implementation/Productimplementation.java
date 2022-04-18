package net.spring.restapi.service.implementation;

import net.spring.restapi.model.Product;
import net.spring.restapi.service.ProductService;
import net.spring.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;
import java.util.List;
@Service
public class Productimplementation implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Productimplementation(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    public Product insert(Product productList){
        return productRepository.save(productList);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public boolean isProductExists(Long id){
        return productRepository.existsById(id);
    }

    @Override
    public Product saveProduct(Product productList) {
        return productRepository.save(productList);
    }


    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }




}
