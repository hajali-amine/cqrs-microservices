package cqrs.microservice.query.receivers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cqrs.microservice.query.events.ProductCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class CreateProductReceiver {
    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = "create-product-queue")
    public void receiveMessage(Message<ProductCreatedEvent> message) throws JsonProcessingException {
        System.out.println("Test");
        System.out.println(message.getPayload());
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
