package cqrs.microservice.query.events;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductRefilledEvent {
    private String ref;
    private int quantity;
}
