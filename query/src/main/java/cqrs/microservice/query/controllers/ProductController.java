package cqrs.microservice.query.controllers;

import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.repositories.ProductRepository;
import cqrs.microservice.query.services.ProductService;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/query")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EventStore eventStore;

    @GetMapping("/")
    public List<Product> getProd(){
        return productService.getProd();
    }

    @GetMapping("/a")
    public List<Object> getProds(){
        return eventStore.readEvents("xz").asStream().map( s -> s.getPayload()).collect(Collectors.toList());
    }
}
