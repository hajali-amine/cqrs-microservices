package cqrs.microservice.query.controllers;

import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.services.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/query")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getProd(){
        return this.productService.getProducts();
    }

    @DeleteMapping("/purge")
    public List<Product> purgeDb(){
        return this.productService.purgeDb();
    }
}
