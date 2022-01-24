package cqrs.microservice.command.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cqrs.microservice.command.models.Product;

import cqrs.microservice.command.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/command")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Product createProduct(@RequestBody Product product) throws JsonProcessingException {
        return this.productService.addProduct(product);
    }
    @GetMapping("/")
    public List<Product> getAll() {
        return this.productService.getProducts();
    }

    @DeleteMapping("/purge")
    public List<Product> purgeDb(){
        return this.productService.purgeDb();
    }
}
