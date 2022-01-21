package cqrs.microservice.query.services;

import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    public List<Product> getProd(){
        return productRepository.findAll();
    }
}
