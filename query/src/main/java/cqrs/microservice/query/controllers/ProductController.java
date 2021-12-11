package cqrs.microservice.query.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/")
    public String queryHi(){
        return "Hello world!";
    }
}
