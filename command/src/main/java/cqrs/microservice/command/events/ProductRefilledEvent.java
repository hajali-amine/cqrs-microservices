package cqrs.microservice.command.events;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductRefilledEvent {
    private final String ref;
    private final int quantity;
}
