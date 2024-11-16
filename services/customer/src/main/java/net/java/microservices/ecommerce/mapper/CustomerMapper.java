package net.java.microservices.ecommerce.mapper;

import net.java.microservices.ecommerce.dto.CustomerRequest;
import net.java.microservices.ecommerce.dto.CustomerResponse;
import net.java.microservices.ecommerce.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if (request == null)
            return null;

        return Customer.builder()
                .id(request.id())
                .address(request.address())
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
