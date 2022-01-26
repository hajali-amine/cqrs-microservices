package cqrs.microservice.command.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@Document(collection = "Products")
public class Product {
    @Id
    private String ref;
    private String name;
    private String description;
    private float price;
    private int quantity;
}
