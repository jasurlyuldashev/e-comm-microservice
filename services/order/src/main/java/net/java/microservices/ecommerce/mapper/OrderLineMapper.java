package net.java.microservices.ecommerce.mapper;

import net.java.microservices.ecommerce.dto.OrderLineRequest;
import net.java.microservices.ecommerce.dto.OrderLineResponse;
import net.java.microservices.ecommerce.entity.Order;
import net.java.microservices.ecommerce.entity.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(Order.builder().id(request.orderId()).build())
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(),orderLine.getQuantity());
    }
}
