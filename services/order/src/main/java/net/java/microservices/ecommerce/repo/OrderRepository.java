package net.java.microservices.ecommerce.repo;

import net.java.microservices.ecommerce.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository  extends JpaRepository<Order, Integer> {

}
