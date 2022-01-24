package cqrs.microservice.query.services;

import cqrs.microservice.query.models.Product;
import cqrs.microservice.query.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProd(){
        return this.productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }
}
