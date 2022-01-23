package cqrs.microservice.query.handlers;

import cqrs.microservice.query.events.ProductCreatedEvent;
import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.services.ProductService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CreateProductHandler {
    @Autowired
    ProductService productService;

    @EventHandler
    @Transactional
    public void on(ProductCreatedEvent event) {
        Product product = new Product(
                event.getProductRef(),
                event.getProductName(),
                event.getProductDescription(),
                event.getProductPrice(),
                event.getProductQuantity()
        );
        this.productService.addProduct(product);
    }
}
