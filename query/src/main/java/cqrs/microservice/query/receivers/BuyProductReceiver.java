package cqrs.microservice.query.receivers;

import cqrs.microservice.query.events.ProductBoughtEvent;
import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.services.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class BuyProductReceiver {
    ProductService productService;

    public BuyProductReceiver(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "buy-product-queue")
    public void receiveMessage(Message<ProductBoughtEvent> message) {
        Product product = this.productService.getProductByRef(message.getPayload().getRef());
        if (product != null) {
            product.setQuantity(product.getQuantity() - 1);
            productService.addProduct(product);
        }
    }
}
