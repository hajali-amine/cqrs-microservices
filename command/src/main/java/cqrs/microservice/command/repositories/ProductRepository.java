package cqrs.microservice.command.repositories;

import cqrs.microservice.command.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByRef(String ref);
}
