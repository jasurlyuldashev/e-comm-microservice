package net.java.microservices.ecommerce.dto;

import net.java.microservices.ecommerce.entity.enums.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
