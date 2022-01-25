package cqrs.microservice.command.queues;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BuyProductQueue {
    public static final String TOPIC_EXCHANGE_NAME = "buy-product-queue";
    public static final String QUEUE_NAME = "buy-product-queue";

    @Bean
    Queue buyQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange buyExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding buyBinding(Queue buyQueue, TopicExchange buyExchange) {
        return BindingBuilder.bind(buyQueue).to(buyExchange).with("buy.product.#");
    }
}
