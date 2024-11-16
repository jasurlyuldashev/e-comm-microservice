package net.java.microservices.ecommerce.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.java.microservices.ecommerce.dto.ProductPurchaseRequest;
import net.java.microservices.ecommerce.dto.ProductPurchaseResponse;
import net.java.microservices.ecommerce.dto.ProductRequest;
import net.java.microservices.ecommerce.dto.ProductResponse;
import net.java.microservices.ecommerce.entity.Product;
import net.java.microservices.ecommerce.exception.ProductPurchaseException;
import net.java.microservices.ecommerce.mapper.ProductMapper;
import net.java.microservices.ecommerce.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();

    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        var productIds = requests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = repository.findAllById(productIds);

        if (productIds.size() != storedProducts.size())
            throw new ProductPurchaseException("One or more products does not exist");

         var storeRequests = requests
                 .stream()
                 .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                 .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storeRequests.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storeRequests.get(i);
            if (product.getAvailableQuantity()<productRequest.quantity())
                throw new ProductPurchaseException("Insufficient stock for "+productRequest.productId());

            var newAvailableQuantity = product.getAvailableQuantity()-productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product not found with id :"+productId));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll().stream().map(mapper::toProductResponse).toList();
    }
}
