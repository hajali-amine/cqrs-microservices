package cqrs.microservice.command.repositories;

import cqrs.microservice.command.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {
}
