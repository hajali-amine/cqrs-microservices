package cqrs.microservice.command.senders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cqrs.microservice.command.events.ProductRefilledEvent;
import cqrs.microservice.command.queues.RefillProductQueue;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class RefillProductSender {
    static final String ROUTING_KEY = "refill.product.";

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public RefillProductSender(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(ProductRefilledEvent productRefilledEvent) throws JsonProcessingException {
        String productRefilledEventJson = this.objectMapper.writeValueAsString(productRefilledEvent);
        Message message = MessageBuilder
                .withBody(productRefilledEventJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        this.rabbitTemplate.convertAndSend(
                RefillProductQueue.QUEUE_NAME,
                ROUTING_KEY + productRefilledEvent.getRef(),
                message
        );
    }
}
