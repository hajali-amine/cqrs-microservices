package cqrs.microservice.query.events;

import lombok.*;


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
