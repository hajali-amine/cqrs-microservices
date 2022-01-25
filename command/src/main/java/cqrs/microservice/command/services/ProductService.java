package cqrs.microservice.command.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import cqrs.microservice.command.events.ProductBoughtEvent;
import cqrs.microservice.command.events.ProductCreatedEvent;
import cqrs.microservice.command.events.ProductRefilledEvent;
import cqrs.microservice.command.models.Product;
import cqrs.microservice.command.repositories.ProductRepository;
import cqrs.microservice.command.senders.BuyProductSender;
import cqrs.microservice.command.senders.CreateProductSender;
import cqrs.microservice.command.senders.RefillProductSender;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CreateProductSender createProductSender;
    private final BuyProductSender buyProductSender;
    private final RefillProductSender refillProductSender;

    public ProductService(ProductRepository productRepository, CreateProductSender createProductSender,
                          BuyProductSender buyProductSender, RefillProductSender refillProductSender) {
        this.productRepository = productRepository;
        this.createProductSender = createProductSender;
        this.buyProductSender = buyProductSender;
        this.refillProductSender = refillProductSender;
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public String createProduct(Product product) throws JsonProcessingException {
        boolean isProductPresent = this.productRepository.findById(product.getRef()).isPresent();
        if (isProductPresent){
            return "The product's reference exists already!";
        }

        Product createdProduct = this.productRepository.save(product);
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                product.getRef(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );
        this.createProductSender.send(productCreatedEvent);
        return createdProduct.getName() + " has been created!";
    }

    public String buyProduct(String ref) throws JsonProcessingException {
        Product boughtProduct = this.productRepository.findByRef(ref);

        if (boughtProduct == null){
            return "Product does not exist!";
        }

        if (boughtProduct.getQuantity() > 0) {
            boughtProduct.setQuantity(boughtProduct.getQuantity() - 1);
            this.productRepository.save(boughtProduct);
            ProductBoughtEvent productBoughtEvent = new ProductBoughtEvent(
                    boughtProduct.getRef()
            );
            this.buyProductSender.send(productBoughtEvent);
            return "Congrats! you bought one " + boughtProduct.getName();
        }

        return "We regret to inform you that! you bought one " + boughtProduct.getName();
    }

    public String refillProduct(String ref, int number) throws JsonProcessingException {
        Product  refilledProduct = this.productRepository.findByRef(ref);

        if (refilledProduct == null){
            return "Product does not exist!";
        }

        if (number <= 0){
            return "You aren't adding anything!";
        }

        refilledProduct.setQuantity(refilledProduct.getQuantity() + number);
        this.productRepository.save(refilledProduct);
        ProductRefilledEvent productRefilledEvent = new ProductRefilledEvent(
                refilledProduct.getRef(),
                number
        );
        this.refillProductSender.send(productRefilledEvent);

        return "Congrats! you refilled " + refilledProduct.getName();
    }

    public List<Product> purgeDb() {
        this.productRepository.findAll().forEach(this.productRepository::delete);
        return this.productRepository.findAll();
    }
}
