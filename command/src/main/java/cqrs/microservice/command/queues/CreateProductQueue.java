package cqrs.microservice.command.queues;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CreateProductQueue {
    public static final String TOPIC_EXCHANGE_NAME = "create-product-queue";
    public static final String QUEUE_NAME = "create-product-queue";

    @Bean
    Queue createQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange createExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding createBinding(Queue createQueue, TopicExchange createExchange) {
        return BindingBuilder.bind(createQueue).to(createExchange).with("create.product.#");
    }
}
