package cqrs.microservice.command.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/")
    public String commandHi(){
        return "Hello world!";
    }
}
