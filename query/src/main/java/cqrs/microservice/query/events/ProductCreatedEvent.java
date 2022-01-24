package cqrs.microservice.query.events;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductCreatedEvent {
    private String ref;
    private String name;
    private String description;
    private float price;
    private int quantity;
}
