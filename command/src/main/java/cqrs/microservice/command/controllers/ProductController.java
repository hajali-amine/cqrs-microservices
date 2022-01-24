package cqrs.microservice.command.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cqrs.microservice.command.models.Product;
import cqrs.microservice.command.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/command")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public String createProduct(@RequestBody Product product) throws JsonProcessingException {
        productService.addProductEvent(product);
        return "Test RabbitMQ";
    }
    @GetMapping("/")
    public List<Product> getAll() {
        return this.productService.getProducts();
    }
}
