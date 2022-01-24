package cqrs.microservice.command.events;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductCreatedEvent {
    private final String ref;
    private final String name;
    private final String description;
    private final float price;
    private final int quantity;
}
