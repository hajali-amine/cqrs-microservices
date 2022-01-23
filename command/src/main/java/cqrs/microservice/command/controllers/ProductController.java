package cqrs.microservice.command.controllers;

import cqrs.microservice.command.commands.CreateProductCommand;
import cqrs.microservice.command.models.Product;
import cqrs.microservice.command.services.ProductService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/command")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    EventStore eventStore;

    @PostMapping("/add")
    public CompletableFuture<String> createProduct(@RequestBody Product product){
        return productService.addProductEvent(product);
    }
    @GetMapping("/")
    public List<Object> getAll() {
//        return this.productService.getProducts();
        return eventStore.readEvents("xz").asStream().map(s -> s.getPayload()).collect(Collectors.toList());
    }
}
