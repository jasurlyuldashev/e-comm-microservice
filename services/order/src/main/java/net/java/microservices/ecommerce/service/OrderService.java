package net.java.microservices.ecommerce.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.java.microservices.ecommerce.dto.*;
import net.java.microservices.ecommerce.exception.BusinessException;
import net.java.microservices.ecommerce.feign.CustomerClient;
import net.java.microservices.ecommerce.feign.PaymentClient;
import net.java.microservices.ecommerce.feign.ProductClient;
import net.java.microservices.ecommerce.kafka.OrderProducer;
import net.java.microservices.ecommerce.mapper.OrderMapper;
import net.java.microservices.ecommerce.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLine;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        var customer = customerClient.findById(request.customerId())
                .orElseThrow(()-> new BusinessException("Cannot create order with customer id " + request.customerId()));
        var purchasedProducts = productClient.purchaseProduct(request.products());

        var order = repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()){
            orderLine.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found for %d", orderId)));

    }
}
