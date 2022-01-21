package cqrs.microservice.query.controllers;

import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.repositories.ProductRepository;
import cqrs.microservice.query.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public List<Product> getProd(){
        return productService.getProd();
    }
}
