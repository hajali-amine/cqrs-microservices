package cqrs.microservice.command.queues;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RefillProductQueue {
    public static final String TOPIC_EXCHANGE_NAME = "refill-product-queue";
    public static final String QUEUE_NAME = "refill-product-queue";

    @Bean
    Queue refillQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange refillExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding refillBinding(Queue refillQueue, TopicExchange refillExchange) {
        return BindingBuilder.bind(refillQueue).to(refillExchange).with("refill.product.#");
    }
}