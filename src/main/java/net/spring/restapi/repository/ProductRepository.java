package net.spring.restapi.repository;

import net.spring.restapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends JpaRepository<Product , Long> {
    //All product operations
}
