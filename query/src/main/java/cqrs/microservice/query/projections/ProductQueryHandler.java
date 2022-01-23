package cqrs.microservice.query.projections;

import cqrs.microservice.query.events.ProductCreatedEvent;
import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.queries.GetProductsQuery;
import cqrs.microservice.query.repositories.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductQueryHandler {
    @Autowired
    private ProductRepository productRepository;

    @QueryHandler
    public List<Product> handle(GetProductsQuery query){
        return this.productRepository.findAll();
    }
}
