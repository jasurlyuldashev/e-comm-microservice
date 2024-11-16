package net.java.microservices.ecommerce.dto;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
