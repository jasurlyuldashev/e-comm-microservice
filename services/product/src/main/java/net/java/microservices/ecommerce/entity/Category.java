package net.java.microservices.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
