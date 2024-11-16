package net.java.microservices.ecommerce.repo;

import net.java.microservices.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}
