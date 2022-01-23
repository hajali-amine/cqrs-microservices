package cqrs.microservice.command.models;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Getter
@Document(collection = "Products")
public class Product {
    @Id
    private final String ref;
    private final String name;
    private final String description;
    private final float price;
    private final int quantity;

    public Product(String ref, String name, String description, float price, int quantity) {
        this.ref = ref;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
