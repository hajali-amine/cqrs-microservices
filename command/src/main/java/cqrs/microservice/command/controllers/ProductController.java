package cqrs.microservice.command.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cqrs.microservice.command.entities.Product;

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

    @PostMapping("/create")
    public String createProduct(@RequestBody Product product) throws JsonProcessingException {
        return this.productService.createProduct(product);
    }

    @PostMapping("/buy/{ref}")
    public String buyProduct(@PathVariable String ref) throws JsonProcessingException {
        return this.productService.buyProduct(ref);
    }

    @PostMapping("/refill/{ref}")
    public String refillProduct(@PathVariable String ref, @RequestParam(name="quantity", defaultValue = "0") int quantity) throws JsonProcessingException {
        return this.productService.refillProduct(ref, quantity);
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
