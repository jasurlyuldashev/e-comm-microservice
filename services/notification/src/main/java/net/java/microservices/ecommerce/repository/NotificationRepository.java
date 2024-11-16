package net.java.microservices.ecommerce.repository;

import net.java.microservices.ecommerce.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String > {
}
