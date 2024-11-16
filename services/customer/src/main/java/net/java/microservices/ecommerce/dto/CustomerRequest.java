package net.java.microservices.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.java.microservices.ecommerce.entity.Address;


public record CustomerRequest(
        String id,
        @NotNull(message = "Firstname is required")
        String firstName,
        @NotNull(message = "Lastname is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "Emails is not valid")
        String email,

        Address address) {

}
