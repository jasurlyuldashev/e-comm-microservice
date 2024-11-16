package net.java.microservices.ecommerce.repository;

import net.java.microservices.ecommerce.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
