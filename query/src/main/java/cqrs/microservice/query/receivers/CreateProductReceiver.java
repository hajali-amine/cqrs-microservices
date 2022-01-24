package cqrs.microservice.query.receivers;

import cqrs.microservice.query.events.ProductCreatedEvent;
import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.services.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class CreateProductReceiver {
    ProductService productService;

    public CreateProductReceiver(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "create-product-queue")
    public void receiveMessage(Message<ProductCreatedEvent> message) {
        Product product = new Product(
                message.getPayload().getRef(),
                message.getPayload().getName(),
                message.getPayload().getDescription(),
                message.getPayload().getPrice(),
                message.getPayload().getQuantity()
        );
        productService.addProduct(product);
    }
}
