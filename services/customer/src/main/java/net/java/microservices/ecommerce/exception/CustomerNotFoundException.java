package net.java.microservices.ecommerce.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
