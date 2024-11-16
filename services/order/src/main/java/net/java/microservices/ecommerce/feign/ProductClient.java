package net.java.microservices.ecommerce.feign;

import net.java.microservices.ecommerce.dto.PurchaseRequest;
import net.java.microservices.ecommerce.dto.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {
    @PostMapping("purchase")
    List<PurchaseResponse> purchaseProduct( @RequestBody List<PurchaseRequest> requests);
}
