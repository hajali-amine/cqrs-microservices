package cqrs.microservice.command.aggregates;

import cqrs.microservice.command.commands.CreateProductCommand;
import cqrs.microservice.command.events.ProductCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductAggregate {
    @AggregateIdentifier
    private String ref;
    private String name;
    private String description;
    private float price;
    private int quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand command){
        // TODO: see why the command is null
        System.out.println("Command handler agg" + command.toString());
        ProductCreatedEvent event = new ProductCreatedEvent(
                command.getProductRef(),
                command.getProductName(),
                command.getProductDescription(),
                command.getProductPrice(),
                command.getProductQuantity());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event){
        System.out.println(event);
        this.ref = event.getProductRef();
        this.name = event.getProductName();
        this.description = event.getProductDescription();
        this.price = event.getProductPrice();
        this.quantity = event.getProductQuantity();
    }
}
