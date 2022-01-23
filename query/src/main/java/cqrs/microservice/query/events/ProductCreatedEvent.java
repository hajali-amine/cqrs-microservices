package cqrs.microservice.query.events;

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

    public String getProductRef() {
        return this.ref;
    }
    public String getProductName() {
        return this.name;
    }
    public String getProductDescription() {
        return this.description;
    }
    public float getProductPrice() {
        return this.price;
    }
    public int getProductQuantity() {
        return this.quantity;
    }
}
