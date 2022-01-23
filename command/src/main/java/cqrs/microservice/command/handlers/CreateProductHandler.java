package cqrs.microservice.command.handlers;

import cqrs.microservice.command.events.ProductCreatedEvent;
import cqrs.microservice.command.models.Product;
import cqrs.microservice.command.services.ProductService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProductHandler {
    @Autowired
    ProductService productService;

    @EventHandler
    public void on(ProductCreatedEvent event){
        System.out.println(event.toString() + "handler");
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
