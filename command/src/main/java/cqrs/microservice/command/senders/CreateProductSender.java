package cqrs.microservice.command.senders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cqrs.microservice.command.events.ProductCreatedEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProductSender {
    static final String topicExchangeName = "create-product-queue";
    static final String routingKey = "create.product.";

    @Autowired
    ObjectMapper objectMapper;

    private final RabbitTemplate rabbitTemplate;

    public CreateProductSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(ProductCreatedEvent productCreatedEvent) throws JsonProcessingException {
        String productCreatedEventJson = objectMapper.writeValueAsString(productCreatedEvent);
        Message message = MessageBuilder
                .withBody(productCreatedEventJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey + "test", message);
    }
}
