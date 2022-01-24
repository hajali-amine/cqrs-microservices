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
    private final ProductRepository productRepository;
    private final CreateProductSender createProductSender;

    public ProductService(ProductRepository productRepository, CreateProductSender createProductSender) {
        this.productRepository = productRepository;
        this.createProductSender = createProductSender;
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public Product addProduct(Product product) throws JsonProcessingException {
        Product productAdded = this.productRepository.save(product);
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                product.getRef(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
        this.createProductSender.send(productCreatedEvent);
        return productAdded;
    }

    public List<Product> purgeDb() {
        this.productRepository.findAll().forEach(this.productRepository::delete);
        return this.productRepository.findAll();
    }
}
