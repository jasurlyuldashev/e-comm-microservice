package net.java.microservices.ecommerce.service;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.java.microservices.ecommerce.dto.CustomerRequest;
import net.java.microservices.ecommerce.dto.CustomerResponse;
import net.java.microservices.ecommerce.entity.Customer;
import net.java.microservices.ecommerce.exception.CustomerNotFoundException;
import net.java.microservices.ecommerce.mapper.CustomerMapper;
import net.java.microservices.ecommerce.repo.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", request.id())
                ));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setAddress(request.address());
    }

    public List<CustomerResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .toList();
    }

    public Boolean existsById(String customerId) {
        return repository.existsById(customerId);
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", customerId)
                ));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
