package cqrs.microservice.command.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@AllArgsConstructor
@Document(collection = "Products")
public class Product {
    @Id
    private final String ref;
    private final String name;
    private final String description;
    private final float price;
    private final int quantity;
}
