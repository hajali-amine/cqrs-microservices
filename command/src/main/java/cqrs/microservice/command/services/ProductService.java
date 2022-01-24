package cqrs.microservice.command.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import cqrs.microservice.command.events.ProductCreatedEvent;
import cqrs.microservice.command.models.Product;
import cqrs.microservice.command.repositories.ProductRepository;
import cqrs.microservice.command.senders.CreateProductSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CreateProductSender createProductSender;

    public ProductService(ProductRepository productRepository, CreateProductSender createProductSender) {
        this.productRepository = productRepository;
        this.createProductSender = createProductSender;
    }

    public Product addProduct(Product product){
        return this.productRepository.save(product);
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public void addProductEvent(Product product) throws JsonProcessingException {
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                product.getRef(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
        createProductSender.send(productCreatedEvent);
//        System.out.println(command);
//        return commandGateway.send(command);
    }
}
