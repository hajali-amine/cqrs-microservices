package cqrs.microservice.command.services;

import cqrs.microservice.command.aggregates.ProductAggregate;
import cqrs.microservice.command.commands.CreateProductCommand;
import cqrs.microservice.command.models.Product;
import cqrs.microservice.command.repositories.ProductRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CommandGateway commandGateway;

    public ProductService(ProductRepository productRepository, CommandGateway commandGateway) {
        this.productRepository = productRepository;
        this.commandGateway = commandGateway;
    }

    public Product addProduct(Product product){
        return this.productRepository.save(product);
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public CompletableFuture<String> addProductEvent(Product product) {
        CreateProductCommand command = new CreateProductCommand(
                product.getRef(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
        System.out.println(command);
        return commandGateway.send(command);
    }
}
