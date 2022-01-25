package cqrs.microservice.query.events;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductBoughtEvent {
    private String ref;
}
