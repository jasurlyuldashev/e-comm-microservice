package net.java.microservices.ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.java.microservices.ecommerce.dto.ProductPurchaseRequest;
import net.java.microservices.ecommerce.dto.ProductPurchaseResponse;
import net.java.microservices.ecommerce.dto.ProductRequest;
import net.java.microservices.ecommerce.dto.ProductResponse;
import net.java.microservices.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseResponse(
            @RequestBody List<ProductPurchaseRequest> requests
    ){
        return ResponseEntity.ok(service.purchaseProducts(requests));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(service.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
