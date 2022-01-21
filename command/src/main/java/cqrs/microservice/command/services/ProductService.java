package cqrs.microservice.command.services;

import cqrs.microservice.command.models.Product;
import cqrs.microservice.command.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product addProduct(){
        return this.productRepository.save(new Product("test", "test", 2.5f, 20));
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }
}
