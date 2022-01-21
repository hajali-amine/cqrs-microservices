package cqrs.microservice.command.controllers;

import cqrs.microservice.command.models.Product;
import cqrs.microservice.command.services.ProductService;
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
    public List<Product> getProds(){
        return this.productService.getProducts();
    }

    @PostMapping("/add")
    public Product addProduct(){
        return this.productService.addProduct();
    }
}
