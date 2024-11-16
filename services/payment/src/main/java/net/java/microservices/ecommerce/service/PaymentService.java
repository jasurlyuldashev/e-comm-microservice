package net.java.microservices.ecommerce.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.java.microservices.ecommerce.dto.PaymentNotificationRequest;
import net.java.microservices.ecommerce.dto.PaymentRequest;
import net.java.microservices.ecommerce.mapper.PaymentMapper;
import net.java.microservices.ecommerce.notification.NotificationProducer;
import net.java.microservices.ecommerce.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final NotificationProducer notificationProducer;
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    public Integer createPayment(@Valid PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
