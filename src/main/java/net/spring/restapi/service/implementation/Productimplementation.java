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

        AtomicReference<Double> taxPerProduct = new AtomicReference<>(0.0);
        AtomicReference<Double> total_tax = new AtomicReference<>(0.0);
        AtomicReference<Double> total_price = new AtomicReference<>(0.0);
        AtomicReference<Double> unitPrice = new AtomicReference<>(0.0);

        List<Product> productList = getAllProducts();
        productList.forEach(product-> {

            if (product.isImported(product.getpName())) {
                if (product.isSalesTaxApplicable(product.getpName())) {
                    taxPerProduct.set(product.getpPrice() * 0.05);
                    unitPrice.set((product.getpPrice() + taxPerProduct.get()) * product.getpCount());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getpCount()));
                    total_price.updateAndGet(v -> v + product.getpPrice());
                }
                else {
                    taxPerProduct.set(product.getpPrice() * 0.15);
                    unitPrice.set((product.getpPrice() + taxPerProduct.get()) * product.getpCount());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getpCount()));
                    total_price.updateAndGet(v -> v + product.getpPrice());
                }
            }
            else {
                if (product.isSalesTaxApplicable(product.getpName())) {
                    unitPrice.set((product.getpPrice() + taxPerProduct.get()) * product.getpCount());
                    total_price.updateAndGet(v -> v + product.getpPrice());
                }
                else {
                    taxPerProduct.set(product.getpPrice() * 0.10);
                    unitPrice.set((product.getpPrice() + taxPerProduct.get()) * product.getpCount());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getpCount()));
                    total_price.updateAndGet(v -> v + product.getpPrice());
                }
            }

        });

        return total_price.get();

    }

    @Override
    public Double calculateTotalTax(){

        AtomicReference<Double> total_tax = new AtomicReference<>(0.0);
        AtomicReference<Double> total_price = new AtomicReference<>(0.0);
        AtomicReference<Double> taxPerProduct = new AtomicReference<>(0.0);
        AtomicReference<Double> unitPrice = new AtomicReference<>(0.0);

        List<Product> productList = getAllProducts();
        productList.forEach(product-> {


            if (product.isImported(product.getpName())) {
                if (product.isSalesTaxApplicable(product.getpName())) {
                    taxPerProduct.set(product.getpPrice() * 0.05);
                    unitPrice.set((product.getpPrice() + taxPerProduct.get()) * product.getpCount());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getpCount()));
                    total_price.updateAndGet(v -> v + product.getpPrice());
                }
                else {
                    taxPerProduct.set(product.getpPrice() * 0.15);
                    unitPrice.set((product.getpPrice() + taxPerProduct.get()) * product.getpCount());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getpCount()));
                    total_price.updateAndGet(v -> v + product.getpPrice());
                }
            }
            else {
                if (product.isSalesTaxApplicable(product.getpName())) {
                    unitPrice.set((product.getpPrice() + taxPerProduct.get()) * product.getpCount());
                    total_price.updateAndGet(v -> v + product.getpPrice());
                }
                else {
                    taxPerProduct.set(product.getpPrice() * 0.10);
                    unitPrice.set((product.getpPrice() + taxPerProduct.get()) * product.getpCount());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getpCount()));
                    total_price.updateAndGet(v -> v + product.getpPrice());
                }
            }

        });

        return total_tax.get();

    }

}
