package cqrs.microservice.query.receivers;

import cqrs.microservice.query.events.ProductRefilledEvent;
import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.services.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class RefillProductReceiver {
    ProductService productService;

    public RefillProductReceiver(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "refill-product-queue")
    public void receiveMessage(Message<ProductRefilledEvent> message) {
        Product product = this.productService.getProductByRef(message.getPayload().getRef());
        if (product != null) {
            product.setQuantity(product.getQuantity() + message.getPayload().getQuantity());
            productService.addProduct(product);
        }
    }
}
