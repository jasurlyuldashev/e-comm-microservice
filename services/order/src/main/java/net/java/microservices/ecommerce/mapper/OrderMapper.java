package net.java.microservices.ecommerce.mapper;

import net.java.microservices.ecommerce.dto.OrderRequest;
import net.java.microservices.ecommerce.dto.OrderResponse;
import net.java.microservices.ecommerce.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return new Order().builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
