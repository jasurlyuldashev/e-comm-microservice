package net.java.microservices.ecommerce.dto;

public record OrderLineResponse(
        Integer id,
        double quantity
) {
}
