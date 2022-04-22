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
        return (List<Product>) productRepository.findAll();
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






    @Override
    public Double calculateTotalPrice(){

        AtomicReference<Double> taxEach = new AtomicReference<>(0.0);
        AtomicReference<Double> tax = new AtomicReference<>(0.0);
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
        AtomicReference<Double> pPrice = new AtomicReference<>(0.0);

        List<Product> productList = getAllProducts();
        productList.forEach(product-> {

            if (product.getpName().contains("imported")) {
                if (product.getpName().contains("book") || product.getpName().contains("food") || product.getpName().contains("medicine")) {
                    taxEach.set(product.getpPrice() * 0.05);
                    pPrice.set((product.getpPrice() + taxEach.get()) * product.getpCount());
                    tax.updateAndGet(v -> v + (taxEach.get() * product.getpCount()));
                    totalPrice.updateAndGet(v -> v + product.getpPrice());
                }
                else {
                    taxEach.set(product.getpPrice() * 0.15);
                    pPrice.set((product.getpPrice() + taxEach.get()) * product.getpCount());
                    tax.updateAndGet(v -> v + (taxEach.get() * product.getpCount()));
                    totalPrice.updateAndGet(v -> v + product.getpPrice());
                }
            }
            else {
                if (product.getpName().contains("book") || product.getpName().contains("food") || product.getpName().contains("medicine")) {
                    pPrice.set((product.getpPrice() + taxEach.get()) * product.getpCount());
                    totalPrice.updateAndGet(v -> v + product.getpPrice());
                }
                else {
                    taxEach.set(product.getpPrice() * 0.10);
                    pPrice.set((product.getpPrice() + taxEach.get()) * product.getpCount());
                    tax.updateAndGet(v -> v + (taxEach.get() * product.getpCount()));
                    totalPrice.updateAndGet(v -> v + product.getpPrice());
                }
            }

        });

        return totalPrice.get();

    }

    @Override
    public Double calculateTotalTax(){

        AtomicReference<Double> tax = new AtomicReference<>(0.0);
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
        AtomicReference<Double> taxEach= new AtomicReference<>(0.0);
        AtomicReference<Double> pPrice = new AtomicReference<>(0.0);

        List<Product> productList = getAllProducts();
        productList.forEach(product-> {


            if (product.getpName().contains("imported")) {
                if (product.getpName().contains("book") || product.getpName().contains("food") || product.getpName().contains("medicine")) {
                    taxEach.set(product.getpPrice() * 0.05);
                    pPrice.set((product.getpPrice() + taxEach.get()) * product.getpCount());
                    tax.updateAndGet(v -> v + (taxEach.get() * product.getpCount()));
                    totalPrice.updateAndGet(v -> v + product.getpPrice());
                }
                else {
                    taxEach.set(product.getpPrice() * 0.15);
                    pPrice.set((product.getpPrice() + taxEach.get()) * product.getpCount());
                    tax.updateAndGet(v -> v + (taxEach.get() * product.getpCount()));
                    totalPrice.updateAndGet(v -> v + product.getpPrice());
                }
            }
            else {
                if (product.getpName().contains("book") || product.getpName().contains("food") || product.getpName().contains("medicine")) {
                    pPrice.set((product.getpPrice() + taxEach.get()) * product.getpCount());
                    totalPrice.updateAndGet(v -> v + product.getpPrice());
                }
                else {
                    taxEach.set(product.getpPrice() * 0.10);
                    pPrice.set((product.getpPrice() + taxEach.get()) * product.getpCount());
                    tax.updateAndGet(v -> v + (taxEach.get() * product.getpCount()));
                    totalPrice.updateAndGet(v -> v + product.getpPrice());
                }
            }

        });

        return tax.get();

    }

}
