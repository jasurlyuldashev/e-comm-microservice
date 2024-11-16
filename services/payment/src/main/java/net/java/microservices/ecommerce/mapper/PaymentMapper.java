package net.java.microservices.ecommerce.mapper;

import jakarta.validation.Valid;
import net.java.microservices.ecommerce.dto.PaymentRequest;
import net.java.microservices.ecommerce.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment( PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .build();
    }
}
