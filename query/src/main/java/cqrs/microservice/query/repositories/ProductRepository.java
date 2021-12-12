package cqrs.microservice.query.repositories;

import cqrs.microservice.query.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends ElasticsearchRepository<Product, UUID> {
    Optional<Product> findById(UUID uuid);
}
