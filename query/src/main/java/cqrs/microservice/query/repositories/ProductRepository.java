package cqrs.microservice.query.repositories;

import cqrs.microservice.query.entities.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.lang.NonNull;

import java.util.List;


public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    @NonNull
    List<Product> findAll();
}
