package net.java.microservices.ecommerce.dto;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
