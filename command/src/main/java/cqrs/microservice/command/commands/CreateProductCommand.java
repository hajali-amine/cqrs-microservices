package cqrs.microservice.command.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private String ref;
    private String name;
    private String description;
    private float price;
    private int quantity;

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
