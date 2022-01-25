package cqrs.microservice.command.events;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductBoughtEvent {
    private final String ref;
}
