package net.java.microservices.ecommerce.dto;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
